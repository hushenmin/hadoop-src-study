package com.gaoying.netty.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xiaohu on 2017/4/28.
 */
public class NioDemo {

    public static void main(String[] args) {
        /**
         * 步骤：
         * 1，打开ServerSocketChannel,用于监听客户端的连接，他是所有客户端连接的父管道。
         * 2，绑定监听端口，设置连接为非阻塞模式
         *  3，创建Reator线程，创建多路复用器并启动线程
         *  4,将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听accept事件。
         *  5,多路复用器在线程run方法无限循环体内轮询准备就绪的key
         *  6,多路复用器监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路。
         *  7，设置客户端为非堵塞模式
         *  8，将新接入的客户端连接注册到Reator线程的多路复用器上，监听读操作，读取客户端发送的网络信息
         *  9，异步读取客户端请求消息到缓冲区。
         *  10，对ByteBuffer进行编码，如果有半包消息指针reset，继续读取后续的报文，将解码成功后的消息封装成Task，投递到业务线程池中，对业务逻辑编排。
         *  11，将pojo对象encode成ByteBuffer,调用SocketChanneld的异步write接口，将消息异步发送给客户端。
         */
        int port = 8088;
        try {
            ServerSocketChannel acceptorSvr = ServerSocketChannel.open();   //1

            acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"),port)); //2
            acceptorSvr.configureBlocking(false);

            Selector selector = Selector.open();//3
           // new Thread(new ReactorTask()).start();

          //  SelectionKey key = acceptorSvr.register(selector,SelectionKey.OP_ACCEPT,ioHandler);//4

            int num = selector.select();//5
            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()){
                SelectionKey k = (SelectionKey)it.next();
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

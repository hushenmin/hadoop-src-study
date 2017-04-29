package com.gaoying.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xiaohu on 2017/4/28.
 */
public class MultiplexerTimeServer implements  Runnable{
    private Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口。
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port),1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time is starting in port... "+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void  stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectedKeys  = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (ClosedSelectorException e ){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            if(selector != null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private void handleInput(SelectionKey key) {
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                //接收新的连接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                try {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    //将新的连接连接到Selector
                    sc.register(selector,SelectionKey.OP_ACCEPT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (key.isReadable()){
                //读数据
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = 0;
                try {
                    readBytes = sc.read(readBuffer);

                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"utf-8");
                    System.out.println("The time server receive order : "+body);
                    String currentTime = "query time order".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "Bad query";
                    doWrite(sc,currentTime);
                }else  if (readBytes < 0){
                    key.cancel();
                    sc.close();
                }else ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            channel.write(writeBuffer);
        }
    }
}

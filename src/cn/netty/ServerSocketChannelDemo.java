package cn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by xiaohu on 2017/4/24.
 */
public class ServerSocketChannelDemo {
    public  static  void  startServer() throws IOException {
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8999));
        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){
                ByteBuffer byteBuffer = ByteBuffer.allocate(48);
                int size = socketChannel.read(byteBuffer);
                while (size>0){
                    byteBuffer.flip();
                    Charset charset = Charset.forName("utf-8");
                    System.out.println(charset.newDecoder().decode(byteBuffer));
                    size = socketChannel.read(byteBuffer);
                }
                ByteBuffer response = ByteBuffer.wrap("hello client ,Request had got!".getBytes("utf-8"));
                socketChannel.write(response);
                response.clear();
                socketChannel.close();
                }
            }
        }



    public static void main(String[] args) throws IOException {
        startServer();
    }
}

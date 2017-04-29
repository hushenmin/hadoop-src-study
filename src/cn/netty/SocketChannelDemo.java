package cn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by xiaohu on 2017/4/25.
 */
public class SocketChannelDemo {
    public static void  startClient() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8999));
        socketChannel.configureBlocking(false);
        String request ="Hello ServerSocketChannel";
        ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes("utf-8"));
        socketChannel.write(byteBuffer);
        socketChannel.close();
        System.out.println("sucess!");
        ByteBuffer rbuf = ByteBuffer.allocate(48);
        int size = socketChannel.read(rbuf);
        while (size >0 ){
            rbuf.flip();
            Charset charset = Charset.forName("utf-8");
            System.out.println(charset.newDecoder().decode(rbuf));
            rbuf.clear();
            size = socketChannel.read(rbuf);
        }
        byteBuffer.clear();
        rbuf.clear();
        Thread.sleep(30000);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
       startClient();
    }
}

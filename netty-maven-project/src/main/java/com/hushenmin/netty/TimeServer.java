package com.hushenmin.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by xiaohu on 2017/4/29.
 */
public class TimeServer {
    public  void  bind(int port ) throws InterruptedException {
        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChildChannelHandler());
        //绑定端口，同步等待成功
        ChannelFuture f = b.bind(port).sync();
        //等待服务端监听端口关闭。
        f.channel().closeFuture().sync();
        //优雅退出，释放线程资源
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        int port = 8888;
        if(args != null && args.length >0){
            port = Integer.valueOf(args[0]);
        }
        try {
            new TimeServer().bind(port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

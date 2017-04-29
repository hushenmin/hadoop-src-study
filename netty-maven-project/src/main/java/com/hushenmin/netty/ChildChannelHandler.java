package com.hushenmin.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by xiaohu on 2017/4/29.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel arg0) throws Exception {
        arg0.pipeline().addLast(new TimeServerHandler());
    }
}


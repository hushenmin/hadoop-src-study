package com.gaoying.netty.nio;

/**
 * Created by xiaohu on 2017/4/28.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8088;
        if(args != null && args.length>0){
            port = Integer.valueOf(args[0]);
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}

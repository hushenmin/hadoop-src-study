package com.gaoying.netty.aio;

/**
 * Created by xiaohu on 2017/4/28.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8099;
        if(args != null && args.length >0){
            port = Integer.valueOf(args[0]);
        }
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer,"Aio-AsyncTimeServerHandler-001").start();
    }
}

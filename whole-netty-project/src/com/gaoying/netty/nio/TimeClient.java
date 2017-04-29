package com.gaoying.netty.nio;

/**
 * Created by xiaohu on 2017/4/28.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8088;
        if(args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        new Thread(new TimeClientHandle("localhost",port),"TimeClient-001").start();
    }
}

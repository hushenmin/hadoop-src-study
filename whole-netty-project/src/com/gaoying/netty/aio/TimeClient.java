package com.gaoying.netty.aio;

/**
 * Created by xiaohu on 2017/4/29.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8099;
        if(args != null && args.length >0){
            port = Integer.valueOf(args[0]);
        }
        new Thread(new  AsyncTimeClientHandler("127.0.0.1",port)).start();
    }
}

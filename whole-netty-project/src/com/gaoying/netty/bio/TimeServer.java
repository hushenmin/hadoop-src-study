package com.gaoying.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaohu on 2017/4/27.
 */
public class TimeServer {
    static int port = 8099;
    public static void main(String[] args) {
        if(null !=args && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                System.err.println("NumberFormatException:"+e.getStackTrace());
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is starting in port ... "+port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new  TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

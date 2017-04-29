package com.gaoying.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaohu on 2017/4/27.
 */
public class TimeServerPseudoSyn {
    static  int port = 8099;
    public static void main(String[] args) {
        if(args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is starting in port  ... "+port);
            Socket socket = null;
            TimeServerHandlerExecutorPool singleExcutor = new TimeServerHandlerExecutorPool(50,10000);
            while (true){
                socket = server.accept();
                singleExcutor.executor(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

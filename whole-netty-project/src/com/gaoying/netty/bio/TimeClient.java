package com.gaoying.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by xiaohu on 2017/4/27.
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = TimeServer.port ;
        if (args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        socket = new Socket("127.0.0.1",port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        out.println("QUERY TIME ORDER");
        System.out.println("send order to server succeed.");
        String resp = in.readLine();
        System.out.println("now is : "+ resp);
        in.close();
        out.close();
    }
}

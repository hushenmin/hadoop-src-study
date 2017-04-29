package com.hadoop.rpc;

import com.java.proxy.Subject;
import com.java.proxy.SubjectImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by xiaohu on 2017/4/19.
 */
public class SubjectServer {
    public static String host = "localhost";
    public static int port = 8083;

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        RPC.Server server = null;
        try {
            server = new RPC.Builder(conf)
                    .setProtocol(Subject.class)
                    .setInstance(new SubjectImpl())
                    .setBindAddress(host)
                    .setPort(port)
                    .setNumHandlers(1)
                    .setVerbose(false)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
    }
}

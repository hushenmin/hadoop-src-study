package com.test.rpc;

import com.test.proxy.Subject;
import com.test.proxy.SubjectImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by xiaohu on 2017/4/19.
 */
public class SubjectServer {
    public static int port = 8888;
    public  static  String host = "localhost";
    public static void main(String[] args) throws IOException {
        Configuration  conf  = new Configuration();
        RPC.Server server = new RPC.Builder(conf)
                .setProtocol(Subject.class)
                .setInstance(new SubjectImpl())
                .setNumHandlers(1)
                .setBindAddress(host)
                .setPort(port)
                .setVerbose(false)
                .build();
        server.start();
    }
}

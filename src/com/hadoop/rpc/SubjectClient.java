package com.hadoop.rpc;

import com.java.proxy.Subject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by xiaohu on 2017/4/19.
 */
public class SubjectClient {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            Subject subject = RPC.getProxy(Subject.class,1l,new InetSocketAddress(SubjectServer.host,SubjectServer.port),conf);
            subject.sayHello();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

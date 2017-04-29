package com.test.rpc;

import com.test.proxy.Subject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by xiaohu on 2017/4/19.
 */
public class SubjectClient {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        Subject proxy = RPC.getProxy(Subject.class,2l,new InetSocketAddress(SubjectServer.host,SubjectServer.port),conf);
        proxy.printHello();
        proxy.sayHello();
    }
}

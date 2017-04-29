package com.java.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by xiaohu on 2017/4/18.
 */
public class ProxyMain {
    public static void main(String[] args) {
      Subject proxy=  (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),new Class[]{Subject.class},new MyHandler(new SubjectImpl()));
        proxy.sayHello();
        proxy.helloWorld();

    }
}

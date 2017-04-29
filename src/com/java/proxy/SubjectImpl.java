package com.java.proxy;


/**
 * Created by xiaohu on 2017/4/18.
 */
public class SubjectImpl implements Subject {
    @Override
    public int sayHello() {
        System.out.println("say hello");
        return 0;
    }

    @Override
    public void helloWorld() {
        System.out.println("hello world");
    }
}

package com.test.proxy;

/**
 * Created by xiaohu on 2017/4/19.
 */
public class SubjectImpl implements Subject {
    @Override
    public int sayHello() {
        System.out.println("say hello");
        return 0;
    }
    @Override
    public void printHello() {
        System.out.println("print hello");
    }
}

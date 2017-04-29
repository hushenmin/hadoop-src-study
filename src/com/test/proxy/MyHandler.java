package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler{
    private Object proxyReal;
    public MyHandler(Object proxyReal){
        this.proxyReal=proxyReal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoker");
        return method.invoke(proxyReal,args);
    }
}
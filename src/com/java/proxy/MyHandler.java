package com.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiaohu on 2017/4/18.
 */
public class MyHandler implements InvocationHandler {
    private Object proxyReal;
    public MyHandler(Object proxyReal){
        this.proxyReal = proxyReal;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoker");
        //做了封装，远程socket nio 去调用服务器，把代理类名称和方法传送给服务器。
        return method.invoke(proxyReal,args);
    }
}

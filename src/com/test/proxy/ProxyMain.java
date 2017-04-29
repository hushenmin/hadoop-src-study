package com.test.proxy;
import com.java.proxy.MyHandler;
import java.lang.reflect.Proxy;

/**
 * Created by xiaohu on 2017/4/19.
 */
public class ProxyMain {
    public static void main(String[] args) {
       Subject proxy=(Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),SubjectImpl.class.getInterfaces(),new MyHandler(new SubjectImpl()));
        proxy.sayHello();
    }
}

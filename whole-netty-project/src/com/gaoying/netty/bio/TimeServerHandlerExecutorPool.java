package com.gaoying.netty.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaohu on 2017/4/27.
 */
public class TimeServerHandlerExecutorPool {
    private ExecutorService excutor;

    public TimeServerHandlerExecutorPool(int maxPoolSize,int queueSize) {
        excutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public void executor(java.lang.Runnable task){
        excutor.execute(task);
    }
}

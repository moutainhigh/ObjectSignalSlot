package com.githup.yafeiwang1240.sso.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolExecutor创建封装
 */
public class ThreadPool {

    public static ThreadPoolExecutor newThreadPoolExecutor() {
        return newThreadPoolExecutor(2, 5, 60000, TimeUnit.MILLISECONDS,  20);
    }

    public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, int capacity) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(capacity);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new IgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        return executor;
    }

    private static class IgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.err.println( r.toString() + " rejected");
        }
    }

    private static class NameTreadFactory implements ThreadFactory {

        private final String mBaseName = "sso-thread-";

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, mBaseName + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

}

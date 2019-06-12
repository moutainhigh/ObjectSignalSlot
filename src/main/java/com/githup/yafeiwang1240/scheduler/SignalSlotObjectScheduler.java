package com.githup.yafeiwang1240.scheduler;

import com.githup.yafeiwang1240.factory.ConnectThreadFactory;
import com.githup.yafeiwang1240.thread.ConnectThread;
import com.githup.yafeiwang1240.thread.ThreadPool;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  信号槽调度系统
 */
public class SignalSlotObjectScheduler implements Closeable {

    private ThreadPoolExecutor threadPoolExecutor;

    public SignalSlotObjectScheduler() {
        threadPoolExecutor = ThreadPool.newThreadPoolExecutor();
    }

    public SignalSlotObjectScheduler(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, int capacity) {
        threadPoolExecutor = ThreadPool.newThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, corePoolSize);
    }

    public void connect(Object send, String signal, Object receive, String slot, Class<?>... params) {
        ConnectThreadFactory.addBaseThread(send, receive, signal, slot, params);
    }

    public void emit(Object send, String signal, Object... args) {
        ConnectThread thread = ConnectThreadFactory.getBaseThread(send, signal, args);
        thread.addParams(args);
        threadPoolExecutor.execute(thread);
    }

    public boolean remove(Object send, String signal, Class<?>... params) {
        return ConnectThreadFactory.remove(send, signal, params);
    }

    public boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params) {
        return ConnectThreadFactory.remove(send, receive, signal, slot, params);
    }

    @Override
    public void close() throws IOException {
        threadPoolExecutor.shutdown();
    }
}

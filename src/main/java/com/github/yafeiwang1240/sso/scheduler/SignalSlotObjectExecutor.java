package com.github.yafeiwang1240.sso.scheduler;

import com.github.yafeiwang1240.sso.factory.ConnectManageFactory;
import com.github.yafeiwang1240.sso.factory.ConnectThreadManageFactory;
import com.github.yafeiwang1240.sso.thread.ThreadPool;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  信号槽调度系统
 */
public class SignalSlotObjectExecutor implements ServerExecutor, Closeable {

    private ThreadPoolExecutor threadPoolExecutor;

    private ConnectManageFactory connectFactory;

    public SignalSlotObjectExecutor() {
        this(4, 10, 120000, TimeUnit.MILLISECONDS, 20) ;
    }

    public SignalSlotObjectExecutor(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, int capacity) {
        threadPoolExecutor = ThreadPool.newThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, capacity);
        init();
    }

    private void init() {
        connectFactory = new ConnectThreadManageFactory();
        ((ConnectThreadManageFactory) connectFactory).setSchedulerHandler((_method, _params) -> {
            _method.addParams(_params);
            threadPoolExecutor.execute(_method);
        });
    }

    @Override
    public void connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception {
        connectFactory.connect(send, receive, signal, slot, params);
    }

    @Override
    public void emit(Object send, String signal, Object... args) {
        connectFactory.emit(send, signal, args);
    }

    @Override
    public boolean remove(Object send, String signal, Class<?>... params) {
        return connectFactory.remove(send, signal, params);
    }

    @Override
    public boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params) {
        return connectFactory.remove(send, receive, signal, slot, params);
    }

    @Override
    public void close() throws IOException {
        threadPoolExecutor.shutdown();
    }
}

package com.github.yafeiwang1240.sso.scheduler;

import com.github.yafeiwang1240.sso.factory.ConnectManageFactory;
import com.github.yafeiwang1240.sso.factory.ConnectContainerManagerFactory;
import com.github.yafeiwang1240.sso.factory.ThreadPoolFactory;

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
        threadPoolExecutor = ThreadPoolFactory.newThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, capacity);
        init();
    }

    private void init() {
        connectFactory = new ConnectContainerManagerFactory();
        ((ConnectContainerManagerFactory) connectFactory).setSchedulerHandler((_method, _params) -> {
            _method.addParams(_params);
            threadPoolExecutor.execute(_method);
        });
    }

    @Override
    public boolean connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception {
        return connectFactory.connect(send, receive, signal, slot, params);
    }

    @Override
    public boolean emit(Object send, String signal, Object[] args) {
        return connectFactory.emit(send, signal, args);
    }


    @Override
    public boolean emit(Object send, String signal, Object[] args, Class<?>[] params) {
        return connectFactory.emit(send, signal, args, params);
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

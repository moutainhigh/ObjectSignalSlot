package com.github.yafeiwang1240.sso.server;

import com.github.yafeiwang1240.sso.scheduler.ServerExecutor;
import com.github.yafeiwang1240.sso.scheduler.SignalSlotObjectExecutor;

import java.util.concurrent.TimeUnit;

/**
 * 信号槽服务
 */
public class SSObjectServer implements AsyncServer {

    private ServerExecutor executor;

    public SSObjectServer() {
        executor = new SignalSlotObjectExecutor();
    }

    public SSObjectServer(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, int capacity) {
        executor = new SignalSlotObjectExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, capacity);
    }

    /**
     * 关联函数
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     */
    @Override
    public synchronized boolean connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception {
        return executor.connect(send, signal, receive, slot, params);
    }

    /**
     * 信号函数
     * @param send
     * @param signal
     * @param args
     */
    @Override
    public boolean emit(Object send, String signal, Object[] args) {
        return executor.emit(send, signal, args);
    }

    @Override
    public boolean emit(Object send, String signal, Object[] args, Class<?>[] params) {
        return executor.emit(send, signal, args, params);
    }


    /**
     * 解除关联
     * @param send
     * @param signal
     * @param params
     * @return
     */
    @Override
    public synchronized boolean remove(Object send, String signal, Class<?>... params) {
        return executor.remove(send, signal, params);
    }


    /**
     * 解除关联
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     * @return
     */
    @Override
    public synchronized boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params) {
        return executor.remove(send, signal, receive, slot, params);
    }

}

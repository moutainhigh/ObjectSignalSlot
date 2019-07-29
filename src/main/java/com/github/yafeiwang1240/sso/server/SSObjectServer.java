package com.github.yafeiwang1240.sso.server;

import com.github.yafeiwang1240.sso.annotation.Signal;
import com.github.yafeiwang1240.sso.annotation.Slot;
import com.github.yafeiwang1240.sso.scheduler.SignalSlotObjectExecutor;
import com.github.yafeiwang1240.sso.annotation.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * 信号槽服务
 */
public class SSObjectServer implements AsyncServer {

    private SignalSlotObjectExecutor executor;

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
    public synchronized void connect(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params) throws Exception {
        connect(send, signal.value(), receive, slot.value(), params);
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
    public synchronized void connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception {
        executor.connect(send, signal, receive, slot, params);
    }

    /**
     * 信号函数
     * @param send
     * @param signal
     * @param args
     */
    @Override
    public synchronized void emit(Object send, @NotNull Signal signal, Object... args) {
        emit(send, signal.value(), args);
    }

    /**
     * 信号函数
     * @param send
     * @param signal
     * @param args
     */
    @Override
    public synchronized void emit(Object send, String signal, Object... args) {
        executor.emit(send, signal, args);
    }

    /**
     * 解除关联
     * @param send
     * @param signal
     * @param params
     */
    @Override
    public synchronized boolean remove(Object send, @NotNull Signal signal, Class<?>... params) {
        return remove(send, signal.value(), params);
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
     */
    @Override
    public synchronized boolean remove(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params) {
        return remove(send, signal.value(), receive, slot.value(), params);
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

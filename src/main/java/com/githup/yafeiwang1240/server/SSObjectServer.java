package com.githup.yafeiwang1240.server;

import com.githup.yafeiwang1240.annotation.NotNull;
import com.githup.yafeiwang1240.annotation.Signal;
import com.githup.yafeiwang1240.annotation.Slot;
import com.githup.yafeiwang1240.scheduler.SignalSlotObjectScheduler;

/**
 * 信号槽服务
 */
public class SSObjectServer {

    private static SignalSlotObjectScheduler scheduler = new SignalSlotObjectScheduler();

    /**
     * 关联函数
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     */
    public static synchronized void connect(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params) {
        scheduler.connect(send, signal.value(), receive, slot.value(), params);
    }

    /**
     * 信号函数
     * @param send
     * @param signal
     * @param args
     */
    public static synchronized void emit(Object send, @NotNull Signal signal, Object... args) {
        scheduler.emit(send, signal.value(), args);
    }

    /**
     * 解除关联
     * @param send
     * @param signal
     * @param params
     */
    public static synchronized boolean remove(Object send, @NotNull Signal signal, Class<?>... params) {
        return scheduler.remove(send, signal.value(), params);
    }

    /**
     * 解除关联
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     */
    public static synchronized boolean remove(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params) {
        return scheduler.remove(send, signal.value(), receive, slot.value(), params);
    }

}

package com.github.yafeiwang1240.sso;

import com.github.yafeiwang1240.sso.server.AsyncServer;
import com.github.yafeiwang1240.sso.server.SSObjectServer;

public class AsyncClient {
    private static AsyncServer server;
    static {
        server = new SSObjectServer();
    }

    /**
     * 连接函数
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     * @return
     * @throws Exception
     */
    public static boolean connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception {
        return server.connect(send, signal, receive, slot, params);
    }

    /**
     * 发出信号
     * @param send
     * @param signal
     * @param args
     * @return
     */
    public static boolean emit(Object send, String signal, Object... args) {
        return server.emit(send, signal, args);
    }

    /**
     * 发出信号
     * @param send
     * @param signal
     * @param args
     * @param params
     * @return
     */
    public static boolean emit(Object send, String signal, Object[] args, Class<?>[] params) {
        return server.emit(send, signal, args, params);
    }

    /**
     * 移除连接
     * @param send
     * @param signal
     * @param params
     * @return
     */
    public static boolean remove(Object send, String signal, Class<?>... params) {
        return server.remove(send, signal, params);
    }

    /**
     * 移除单连接
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     * @return
     */
    public static boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params) {
        return server.remove(send, signal, receive, slot, params);
    }
}

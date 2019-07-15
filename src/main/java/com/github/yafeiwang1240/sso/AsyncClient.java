package com.github.yafeiwang1240.sso;

import com.github.yafeiwang1240.sso.annotation.Signal;
import com.github.yafeiwang1240.sso.annotation.Slot;
import com.github.yafeiwang1240.sso.annotation.NotNull;
import com.github.yafeiwang1240.sso.server.AsyncServer;
import com.github.yafeiwang1240.sso.server.SSObjectServer;

public class AsyncClient {
    private static AsyncServer server;
    static {
        server = new SSObjectServer();
    }

    public static void connect(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params){
        server.connect(send, signal, receive, slot, params);
    }
    public static void connect(Object send, String signal, Object receive, String slot, Class<?>... params) {
        server.connect(send, signal, receive, slot, params);
    }
    public static void emit(Object send, @NotNull Signal signal, Object... args) {
        server.emit(send, signal, args);
    }
    public static void emit(Object send, String signal, Object... args) {
        server.emit(send, signal, args);
    }
    public static boolean remove(Object send, @NotNull Signal signal, Class<?>... params){
        return server.remove(send, signal, params);
    }
    public static boolean remove(Object send, String signal, Class<?>... params) {
        return server.remove(send, signal, params);
    }
    public static  boolean remove(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params) {
        return server.remove(send, signal, receive, slot, params);
    }

    public static boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params) {
        return server.remove(send, signal, receive, slot, params);
    }
}

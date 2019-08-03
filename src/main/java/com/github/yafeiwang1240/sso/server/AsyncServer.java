package com.github.yafeiwang1240.sso.server;

public interface AsyncServer {
    boolean connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception;
    boolean emit(Object send, String signal, Object[] args);
    boolean emit(Object send, String signal, Object[] args, Class<?>[] params);
    boolean remove(Object send, String signal, Class<?>... params);
    boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params);
}

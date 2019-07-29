package com.github.yafeiwang1240.sso.scheduler;

public interface ServerExecutor {
    void connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception;
    void emit(Object send, String signal, Object... args);
    boolean remove(Object send, String signal, Class<?>... params);
    boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params);
}
package com.githup.yafeiwang1240.sso.scheduler;

public interface ServerScheduler {
    void connect(Object send, String signal, Object receive, String slot, Class<?>... params);
    void emit(Object send, String signal, Object... args);
    boolean remove(Object send, String signal, Class<?>... params);
    boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params);
}
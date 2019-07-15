package com.github.yafeiwang1240.sso.server;

import com.github.yafeiwang1240.sso.annotation.NotNull;
import com.github.yafeiwang1240.sso.annotation.Signal;
import com.github.yafeiwang1240.sso.annotation.Slot;

public interface AsyncServer {
    void connect(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params);
    void connect(Object send, String signal, Object receive, String slot, Class<?>... params);
    void emit(Object send, @NotNull Signal signal, Object... args);
    void emit(Object send, String signal, Object... args);
    boolean remove(Object send, @NotNull Signal signal, Class<?>... params);
    boolean remove(Object send, String signal, Class<?>... params);
    boolean remove(Object send, @NotNull Signal signal, Object receive, @NotNull Slot slot, Class<?>... params);
    boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params);
}

package com.github.yafeiwang1240.sso.factory;

public interface ConnectManageFactory {

    void emit(Object signalObject, String signal, Object... params);

    void connect(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params);

    boolean remove(Object signalObject, String signal, Class<?>... params);

    boolean remove(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params);
}

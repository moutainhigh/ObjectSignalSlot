package com.github.yafeiwang1240.sso.factory;

public interface ConnectManageFactory {

    boolean emit(Object signalObject, String signal, Object[] args);

    boolean emit(Object signalObject, String signal, Object[] args, Class<?>[] params);

    boolean connect(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params) throws Exception;

    boolean remove(Object signalObject, String signal, Class<?>... params);

    boolean remove(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params);
}

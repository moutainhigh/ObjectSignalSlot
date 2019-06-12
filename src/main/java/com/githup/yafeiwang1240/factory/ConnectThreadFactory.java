package com.githup.yafeiwang1240.factory;

import com.githup.yafeiwang1240.thread.ConnectThread;
import com.githup.yafeiwang1240.utils.HashKeyTools;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectThreadFactory {

    private static Map<String, ConnectThread> baseThreadMap = new ConcurrentHashMap<>();

    public static ConnectThread getBaseThread(Object signalObject, String signal, Object... params) {
        String hashKey = HashKeyTools.toHashKey(getHashArrays(signalObject, signal, params));
        return baseThreadMap.get(hashKey);
    }

    public static void addBaseThread(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params) {
        String hashKey = HashKeyTools.toHashKey(getHashArrays(signalObject, signal, params));
        ConnectThread thread = baseThreadMap.get(hashKey);
        if(thread == null) {
            Connect connect = new Connect(signalObject, slotObject, signal, slot, params);
            thread = new ConnectThread();
            thread.addConnect(connect);
            baseThreadMap.put(hashKey, thread);
        }else if(!thread.containsConnect(slotObject, slot)) {
            Connect connect = new Connect(signalObject, slotObject, signal, slot, params);
            thread.addConnect(connect);
        }
    }

    public static boolean remove(Object signalObject, String signal, Class<?>... params) {
        String hashKey = HashKeyTools.toHashKey(getHashArrays(signalObject, signal, params));
        return baseThreadMap.remove(hashKey) != null;
    }

    public static boolean remove(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params) {
        String hashKey = HashKeyTools.toHashKey(getHashArrays(signalObject, signal, params));
        ConnectThread thread = baseThreadMap.get(hashKey);
        if(thread == null) {
           return false;
        }
        boolean result = thread.remove(slotObject, slot);
        if(thread.isEmpty()) {
            baseThreadMap.remove(hashKey);
        }
        return result;
    }

    private static Object[] getHashArrays(Object obj, String type, Object... params) {
        Object[] arrays = new Object[2 + params.length];
        arrays[0] = obj;
        arrays[1] = type;
        for(int i = 0; i < params.length; i++){
            if(params[i] instanceof Class<?>) {
                arrays[i] = params[i];
            }else {
                Class clz = params[i].getClass();
                Field f = null;
                try {
                    f = clz.getDeclaredField("TYPE");
                } catch (NoSuchFieldException e) {

                }
                if(f != null) {
                    Class _clz = null;
                    try {
                        _clz = (Class) f.get(null);
                    } catch (IllegalArgumentException | IllegalAccessException e) {

                    }
                    arrays[i] = _clz == null ? clz : _clz;
                } else {
                    arrays[i] = clz;
                }
            }
        }
        return arrays;
    }
}

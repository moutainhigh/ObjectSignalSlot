package com.githup.yafeiwang1240.sso.thread;

import com.githup.yafeiwang1240.sso.factory.Connect;
import com.githup.yafeiwang1240.sso.utils.HashKeyTools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectThread implements Runnable {

    private Map<String, Connect> connects = new ConcurrentHashMap<>();

    private Object[] args;

    public void addConnect(Connect connect) {
        String hashKey = HashKeyTools.toHashKey(connect.getSlotObject(), connect.getSlot());
        if(!connects.containsKey(hashKey)) {
            connects.put(hashKey, connect);
        }
    }

    public boolean containsConnect(Object slotObject, String slot) {
        String hashKey = HashKeyTools.toHashKey(slotObject, slot);
        return connects.containsKey(hashKey);
    }

    public boolean remove(Object slotObject, String slot) {
        String hashKey = HashKeyTools.toHashKey(slotObject, slot);
        return connects.remove(hashKey) != null;
    }

    public boolean isEmpty() {
        return connects.isEmpty();
    }

    public void addParams(Object... args) {
        this.args = args;
    }

    @Override
    public void run() {
        Method method;
        for(String key : connects.keySet()) {
            Connect connect = connects.get(key);
            try {
                if(connect.getSlotObject() instanceof Class) {
                    method = ((Class<Object>) connect.getSlotObject()).getMethod(connect.getSlot(), connect.getParams());
                } else {
                    method = connect.getSlotObject().getClass().getMethod(connect.getSlot(), connect.getParams());
                }
                int aLen = args.length;
                int pLen = connect.getParams().length;
                Object [] t;
                if(aLen > pLen) {
                    t = Arrays.copyOf(args, pLen);
                }else {
                    t = args;
                }
                method.invoke(connect.getSlotObject(), t);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

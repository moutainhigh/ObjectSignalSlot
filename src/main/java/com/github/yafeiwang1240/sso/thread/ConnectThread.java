package com.github.yafeiwang1240.sso.thread;

import com.github.yafeiwang1240.sso.factory.Connect;
import com.github.yafeiwang1240.sso.utils.HashKeyTools;

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
        for (Map.Entry<String, Connect> entry : connects.entrySet()) {
            Connect connect = entry.getValue();
            try {
                method = connect.getMethod();
                int aLen = args.length;
                int pLen = connect.getParams().length;
                Object [] t;
                if(aLen > pLen) {
                    t = Arrays.copyOf(args, pLen);
                }else {
                    t = args;
                }
                method.invoke(connect.getInvoke(), t);
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

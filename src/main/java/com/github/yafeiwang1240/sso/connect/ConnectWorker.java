package com.github.yafeiwang1240.sso.connect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectWorker implements Runnable {

    private List<Connect> connects = new ArrayList<>();

    private Object[] args;

    public boolean addConnect(Connect connect) {
        if (connects.contains(connect)) {
            return false;
        }
        return connects.add(connect);
    }

    public Connect remove(Object slotObject, String slot) {
        for (int i = 0; i < connects.size(); i++) {
            if (connects.get(i).equals(slotObject, slot)) {
                return connects.remove(i);
            }
        }
        return null;
    }

    public boolean contains(Object slotObject, String slot) {
        for (int i = 0; i < connects.size(); i++) {
            if (connects.get(i).equals(slotObject, slot)) {
                return true;
            }
        }
        return false;
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
        for (Connect connect : connects) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

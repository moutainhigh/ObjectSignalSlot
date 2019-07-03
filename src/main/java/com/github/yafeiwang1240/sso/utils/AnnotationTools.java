package com.github.yafeiwang1240.sso.utils;

import com.github.yafeiwang1240.sso.annotation.Signal;
import com.github.yafeiwang1240.sso.annotation.Slot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationTools {

    public static Signal getSignal(Class<?> clz, String name) throws NoSuchFieldException, SecurityException {
        Field field = null;
        field = clz.getDeclaredField(name);
        Signal signal = null;
        signal = field.getAnnotation(Signal.class);
        return signal;
    }

    public static Slot getSlot(Class<?> clz, String name, Class<?>... params) throws NoSuchMethodException, SecurityException {
        Method method = null;
        if(params == null || params.length <= 0) {
            method = clz.getDeclaredMethod(name);
        } else {
            method = clz.getDeclaredMethod(name, params);
        }
        Slot slot = null;
        slot = method.getAnnotation(Slot.class);
        return slot;
    }
}

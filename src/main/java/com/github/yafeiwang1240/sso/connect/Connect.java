package com.github.yafeiwang1240.sso.connect;

import java.lang.reflect.Method;

public final class Connect {

    private Object slotObject;

    private String slot;

    private Class<?>[] params;

    private Method method;

    private Object invoke;

    public Connect(Object slotObject, String slot, Class<?>... params) throws Exception {
        setParams(params);
        setSlot(slot);
        setSlotObject(slotObject);
        if(getSlotObject() instanceof Class) {
            method = ((Class) getSlotObject()).getMethod(getSlot(), getParams());
            invoke = ((Class) getSlotObject()).newInstance();
        } else {
            method = getSlotObject().getClass().getMethod(getSlot(), getParams());
            invoke = getSlotObject();
        }
    }

    public Object getSlotObject() {
        return slotObject;
    }

    public void setSlotObject(Object slotObject) {
        this.slotObject = slotObject;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public void setParams(Class<?>... params) {
        this.params = params;
    }

    public Method getMethod() {
        return method;
    }

    public Object getInvoke() {
        return invoke;
    }

    @Override
    public int hashCode() {
        int result = slotObject == null ? 0 : slotObject.hashCode();
        result = result * 31 + (slot == null ? 0 : slot.hashCode());
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                result = result * 31 + (params[i] == null ? 0 : params[i].hashCode());
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Connect)) {
            return false;
        }
        Connect that = (Connect) o;
        if (!that.getSlotObject().equals(slotObject)) {
            return false;
        }
        if (!that.getSlot().equals(slot)) {
            return false;
        }
        if (params != null && that.getParams() == null) {
            return false;
        } else if (params == null && that.getParams() != null) {
            return false;
        } else if (params != null && that.getParams() != null && params.length != that.getParams().length) {
            return false;
        } else if (params != null && that.getParams() != null) {
            for (int i = 0; i < params.length; i++) {
                if (!params[i].equals(that.getParams()[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equals(Object slotObject, String slot) {
        if (!this.getSlotObject().equals(slotObject)) {
            return false;
        }
        if (!this.getSlot().equals(slot)) {
            return false;
        }
        return true;
    }
}

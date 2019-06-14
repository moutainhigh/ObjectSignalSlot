package com.githup.yafeiwang1240.sso.factory;

public final class Connect {

    private Object signalObject;

    private Object slotObject;

    private String signal;

    private String slot;

    private Class<?>[] params;

    Connect(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params) {
        setParams(params);
        setSignal(signal);
        setSlot(slot);
        setSignalObject(signalObject);
        setSlotObject(slotObject);
    }

    public Object getSignalObject() {
        return signalObject;
    }

    public void setSignalObject(Object signalObject) {
        this.signalObject = signalObject;
    }

    public Object getSlotObject() {
        return slotObject;
    }

    public void setSlotObject(Object slotObject) {
        this.slotObject = slotObject;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
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
}

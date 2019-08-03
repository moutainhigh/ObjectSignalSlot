package com.github.yafeiwang1240.sso.connect;

import com.github.yafeiwang1240.sso.factory.ConnectFactory;

import java.lang.reflect.Field;

public class ConnectContainer {
    private Object signalObject;
    private String signal;
    private Class<?>[] params;
    private ConnectWorker connectWorker;

    public ConnectContainer(Object signalObject, String signal, Class<?>[] params) {
        connectWorker = new ConnectWorker();
        this.signal = signal;
        this.signalObject = signalObject;
        this.params = params;
    }

    public boolean addConnect(Object slotObject, String slot) throws Exception {
        if (connectWorker.contains(slotObject, slot)) {
            return false;
        }
        return connectWorker.addConnect(ConnectFactory.newConnect(slotObject, slot, params));
    }

    public Object getSignalObject() {
        return signalObject;
    }

    public String getSignal() {
        return signal;
    }
    public Class<?>[] getParams() {
        return params;
    }

    public ConnectWorker getConnectWorker() {
        return connectWorker;
    }

    @Override
    public int hashCode() {
        int result = signalObject == null ? 0 : signalObject.hashCode();
        result = result * 31 + (signal == null ? 0 : signal.hashCode());
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
        if (!(o instanceof ConnectContainer)) {
            return false;
        }
        ConnectContainer that = (ConnectContainer) o;
        if (!that.getSignalObject().equals(signalObject)) {
            return false;
        }
        if (!that.getSignal().equals(signal)) {
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

    public boolean equals(Object signalObject, String signal, Class<?>[] params) {
        if (!this.getSignalObject().equals(signalObject)) {
            return false;
        }
        if (!this.getSignal().equals(signal)) {
            return false;
        }
        if (params != null && this.getParams() == null) {
            return false;
        } else if (params == null && this.getParams() != null) {
            return false;
        } else if (params != null && this.getParams() != null && params.length != this.getParams().length) {
            return false;
        } else if (params != null && this.getParams() != null) {
            for (int i = 0; i < params.length; i++) {
                if (!params[i].equals(this.getParams()[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 模糊匹配
     * @param args
     * @return
     */
    public boolean equals(Object[] args) {
        if (args != null && this.getParams() == null) {
            return false;
        } else if (args == null && this.getParams() != null) {
            return false;
        } else if (args != null && this.getParams() != null && args.length != this.getParams().length) {
            return false;
        } else if (args != null && this.getParams() != null) {
            for (int i = 0; i < args.length; i++) {
                if (!args[i].getClass().equals(this.getParams()[i])) {
                    Field field = null;
                    try {
                        field = args[i].getClass().getDeclaredField("TYPE");
                    } catch (Exception e) {
                        // ignore
                    }
                    if (field == null) {
                        return false;
                    }
                    Class<?> clazz = null;
                    try {
                        clazz = (Class<?>) field.get(null);
                    } catch (Exception e) {
                        // ignore
                    }
                    if (clazz == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

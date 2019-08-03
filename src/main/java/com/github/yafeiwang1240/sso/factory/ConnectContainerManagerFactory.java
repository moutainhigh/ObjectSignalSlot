package com.github.yafeiwang1240.sso.factory;

import com.github.yafeiwang1240.sso.connect.ConnectContainer;
import com.github.yafeiwang1240.sso.connect.ConnectWorker;
import com.github.yafeiwang1240.sso.handler.ConnectExecutorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 信号槽管理工厂
 */
public class ConnectContainerManagerFactory implements ConnectManageFactory {

    private Map<String, List<ConnectContainer>> baseContainerMap = new ConcurrentHashMap<>();

    private ConnectExecutorHandler<ConnectWorker, Object> executorHandler;


    public void setSchedulerHandler(ConnectExecutorHandler<ConnectWorker, Object> schedulerHandler) {
        this.executorHandler = schedulerHandler;
    }

    @Override
    public boolean emit(Object signalObject, String signal, Object[] args) {
        String key = getKey(signalObject, signal);
        List<ConnectContainer> connectContainers = baseContainerMap.get(key);
        if (connectContainers == null) {
            return false;
        }
        Class<?>[] params;
        if (args == null) {
            params = null;
        } else {
            params = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                params[i] = args[i].getClass();
            }
        }
        ConnectContainer connectContainer = null;
        for (ConnectContainer container : connectContainers) {
            if (container.equals(signalObject, signal, params)) {
                connectContainer = container;
                break;
            }
        }
        if (connectContainer != null) {
            executorHandler.invoke(connectContainer.getConnectWorker(), args);
            return true;
        }
        // 开始模糊匹配
        for (ConnectContainer container : connectContainers) {
            if (container.equals(args)) {
                connectContainer = container;
            }
        }
        if (connectContainer != null) {
            executorHandler.invoke(connectContainer.getConnectWorker(), args);
            return true;
        }
        return false;
    }

    @Override
    public boolean emit(Object signalObject, String signal, Object[] args, Class<?>[] params) {
        String key = getKey(signalObject, signal);
        List<ConnectContainer> connectContainers = baseContainerMap.get(key);
        if (connectContainers == null) {
            return false;
        }
        ConnectContainer connectContainer = null;
        for (ConnectContainer container : connectContainers) {
            if (container.equals(signalObject, signal, params)) {
                connectContainer = container;
                break;
            }
        }
        if (connectContainer != null) {
            executorHandler.invoke(connectContainer.getConnectWorker(), args);
            return true;
        }
        return false;
    }

    @Override
    public boolean connect(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params) throws Exception {
        String key = getKey(signalObject, signal);
        List<ConnectContainer> connectContainers;
        if ((connectContainers = baseContainerMap.get(key)) == null) {
            connectContainers = new ArrayList<>();
            baseContainerMap.put(key, connectContainers);
        }
        ConnectContainer connectContainer = null;
        for (ConnectContainer container : connectContainers) {
            if (container.equals(signalObject, signal, params)) {
                connectContainer = container;
                break;
            }
        }
        if (connectContainer == null) {
            connectContainer = new ConnectContainer(signalObject,signal, params);
            connectContainers.add(connectContainer);
        }
        return connectContainer.addConnect(slotObject, slot);
    }

    @Override
    public boolean remove(Object signalObject, String signal, Class<?>... params) {
        String key = getKey(signalObject, signal);
        List<ConnectContainer> connectContainers = baseContainerMap.get(key);
        if (connectContainers == null) {
            return false;
        }
        ConnectContainer connectContainer = null;
        for (ConnectContainer container : connectContainers) {
            if (container.equals(signalObject, signal, params)) {
                return connectContainers.remove(container);
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object signalObject, Object slotObject, String signal, String slot, Class<?>... params) {
        String key = getKey(signalObject, signal);
        List<ConnectContainer> connectContainers = baseContainerMap.get(key);
        if (connectContainers == null) {
            return false;
        }
        ConnectContainer connectContainer = null;
        for (ConnectContainer container : connectContainers) {
            if (container.equals(signalObject, signal, params)) {
                connectContainer = container;
            }
        }
        if (connectContainer == null) {
            return false;
        }
        boolean result = connectContainer.getConnectWorker().remove(slotObject, slot) != null;
        if (connectContainer.getConnectWorker().isEmpty()) {
            connectContainers.remove(connectContainer);
        }
        return result;
    }

    private String getKey(Object o, String s) {
        return o + "_" + s;
    }

}

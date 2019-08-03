package com.github.yafeiwang1240.sso.factory;

import com.github.yafeiwang1240.sso.connect.Connect;

public class ConnectFactory {
    public static Connect newConnect(Object slotObject, String slot, Class<?>... params) throws Exception {
        return new Connect(slotObject, slot, params);
    }
}

package com.githup.yafeiwang1240.sso.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.ArrayList;
import java.util.List;

public class HashKeyTools {

    private static final String EMPTY = "";

    public static String toHashKey(Object... objects) {
        if(objects == null) {
            return null;
        }
        if(objects.length < 0) {
            return EMPTY;
        }

        List<byte[]> arrays = new ArrayList<>(objects.length);

        for(int i = 0; i < objects.length; i++) {
            if(objects[i] == null) {
                continue;
            }
            byte[] originBytes = objects[i].toString().getBytes(Charsets.UTF_8);
            byte[] val = new byte[originBytes.length + 4];
            byte[] hashBytes = Hashing.murmur3_32().hashBytes(originBytes).asBytes();
            int j;
            for(j = 0; j < 4; ++j) {
                val[j] = hashBytes[j];
            }

            for(j = 0; j < originBytes.length; ++j) {
                val[j + 4] = originBytes[j];
            }

            arrays.add(val);
        }

        return ArraysToString(arrays);
    }

    private static String ArraysToString(List<byte[]> bytes) {
        int length = 0;
        for(int i = 0; i < bytes.size(); i++) {
            length += bytes.get(i).length;
        }
        byte[] val = new byte[length];
        int index = 0;
        for(int i = 0; i < bytes.size(); i++) {
            byte[] bs = bytes.get(i);
            for(int j = 0; j < bs.length; j++) {
                val[index++] = bs[j];
            }
        }
        return new String(val);
    }
}

package com.github.yafeiwang1240;

import com.github.yafeiwang1240.sso.AsyncClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        try {
            System.out.println("hello world");
            Signal signal = new Signal();
            Slot slot = new Slot();
            AsyncClient.connect(signal, "signal", slot, "slot", String.class);
            AsyncClient.connect(signal, "signal", slot, "slot", String.class, int.class);
            AsyncClient.emit(signal, "signal", "hello");
            AsyncClient.emit(signal, "signal", "hello", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Signal {
        public String singal = "1";
    }

    public static class Slot {
        public void slot(String msg) {
            System.out.println("slot: " + msg);
        }
        public void slot(String msg, int val) {
            System.out.println("slot: " + msg + ", val: " + val);
        }
    }
}

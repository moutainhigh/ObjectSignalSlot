package com.githup.yafeiwang1240;

import com.githup.yafeiwang1240.annotation.Signal;
import com.githup.yafeiwang1240.annotation.Slot;
import com.githup.yafeiwang1240.server.SSObjectServer;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class App 
{


    @Signal("signal")
    private String signal;

    @Slot("slot")
    public void slot(String str) {
        System.out.println(str);
    }

    @Slot("slot")
    public void slot(String str, int i) {
        System.out.println(str + ": " + i);
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App app = new App();
        Field field = null;
        Method method1 = null;
        Method method2 = null;
        try {
            field = App.class.getDeclaredField("signal");
            method1 = App.class.getDeclaredMethod("slot", String.class);
            method2 = App.class.getDeclaredMethod("slot", String.class, int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Signal sg = field.getAnnotation(Signal.class);
        Slot sl =  method1.getAnnotation(Slot.class);
        Slot sl_2 =  method2.getAnnotation(Slot.class);
        SSObjectServer.connect(app, sg, app, sl, String.class);
        SSObjectServer.connect(app, sg, app, sl_2, String.class, int.class);

        SSS(sg, sl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SSObjectServer.emit(app, sg, "这是信号槽异步调用1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SSObjectServer.emit(app, sg, "这是信号槽异步调用2", i);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SSObjectServer.remove(app, sg, app, sl, String.class);
    }


    public static void SSS( Signal sig, Slot slot) {
        System.out.println(sig.value() + "::" + slot.value());
        System.out.println(int.class);
    }
}

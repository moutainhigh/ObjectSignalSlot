# oss(Object signal slot)异步调用架构

### 1、接口说明

```java
public class AsyncClient {
    private static AsyncServer server;
    static {
        server = new SSObjectServer();
    }

    /**
     * 连接函数
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     * @return
     * @throws Exception
     */
    public static boolean connect(Object send, String signal, Object receive, String slot, Class<?>... params) throws Exception {
        return server.connect(send, signal, receive, slot, params);
    }

    /**
     * 发出信号
     * @param send
     * @param signal
     * @param args
     * @return
     */
    public static boolean emit(Object send, String signal, Object... args) {
        return server.emit(send, signal, args);
    }

    /**
     * 发出信号
     * @param send
     * @param signal
     * @param args
     * @param params
     * @return
     */
    public static boolean emit(Object send, String signal, Object[] args, Class<?>[] params) {
        return server.emit(send, signal, args, params);
    }

    /**
     * 移除连接
     * @param send
     * @param signal
     * @param params
     * @return
     */
    public static boolean remove(Object send, String signal, Class<?>... params) {
        return server.remove(send, signal, params);
    }

    /**
     * 移除单连接
     * @param send
     * @param signal
     * @param receive
     * @param slot
     * @param params
     * @return
     */
    public static boolean remove(Object send, String signal, Object receive, String slot, Class<?>... params) {
        return server.remove(send, signal, receive, slot, params);
    }
}
```



### 2、使用示例

```java
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
```


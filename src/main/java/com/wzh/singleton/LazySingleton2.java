package com.wzh.singleton;

import java.io.Serializable;

/**
 * 懒汉式单例模式 - 第二种
 * 外部类加载的时候同时加载内部类，JVM 保证单例
 */
public class LazySingleton2 implements Serializable {

    private LazySingleton2(){}

    public static LazySingleton2 getInstance(){
        return InnerLazy.instance;
    }


    private static class InnerLazy {
        private static final LazySingleton2 instance=new LazySingleton2();
    }
    // 重写readResolve，只不过是覆盖了反序列化出来的对象
    // 还是创建了两次，发生在 JVM 层面，相对安全
    // 之前反序列化出的对象会被GC 回收
    // 防止序列化破坏单例
    private Object readResolve(){
        return InnerLazy.instance;
    }
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(() -> {
                System.out.println("1===="+LazySingleton2.getInstance().hashCode());
            }).start();
        }

    }
}

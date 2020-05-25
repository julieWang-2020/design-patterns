package com.wzh.singleton;

/**
 * 懒汉式单例模式 - 第一种
 */
public class LazySingleton1 {

    // volatile 在并发是保持变量的可见性
    private volatile static LazySingleton1 instance;

    private LazySingleton1(){}

    // synchronized 保持同步
    @Deprecated
    public synchronized static LazySingleton1 getInstance1(){
        if(instance == null){
            instance=new LazySingleton1();
        }
        return instance;
    }
    public static LazySingleton1 getInstance2(){
        if(instance == null){
            synchronized (LazySingleton1.class){
                if(instance == null){
                    instance=new LazySingleton1();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(() -> {
                System.out.println("1===="+LazySingleton1.getInstance1().hashCode());
            }).start();
        }

        for(int i=0;i<100;i++){
            new Thread(() -> {
                System.out.println("2===="+LazySingleton1.getInstance2().hashCode());
            }).start();
        }
    }

}

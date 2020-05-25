package com.wzh.singleton;

/**
 * 懒汉式单例模式 - 第一种
 */
public enum  LazySingleton3 {

    INSTANCE;



    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(() -> {
                System.out.println("1===="+ LazySingleton3.INSTANCE.hashCode());
            }).start();
        }

    }
}

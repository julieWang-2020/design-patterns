package com.wzh.singleton;

/**
 * 懒汉式单例模式 - 第一种
 */
public class LazySingleton2 {

    private LazySingleton2(){}

    public static LazySingleton2 getInstance(){
        return InnerLazy.instance;
    }


    private static class InnerLazy {
        private static final LazySingleton2 instance=new LazySingleton2();
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(() -> {
                System.out.println("1===="+LazySingleton2.getInstance().hashCode());
            }).start();
        }

    }
}

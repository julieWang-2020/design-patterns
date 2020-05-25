package com.wzh.singleton;

/**
 * 饿汉式单例模式 第二种
 */
public class HungrySingleton2 {

    private static final HungrySingleton2 instance;

    static {
        instance=new HungrySingleton2();
    }

    private HungrySingleton2(){}

    public static HungrySingleton2 getInstance(){
        return instance;
    }
}

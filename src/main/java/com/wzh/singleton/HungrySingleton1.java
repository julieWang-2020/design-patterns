package com.wzh.singleton;

/**
 * 饿汉式单例模式 第一种
 */
public class HungrySingleton1 {

    private static final HungrySingleton1 instance=new HungrySingleton1();

    private HungrySingleton1(){}

    public static HungrySingleton1 getInstance(){
        return instance;
    }
}

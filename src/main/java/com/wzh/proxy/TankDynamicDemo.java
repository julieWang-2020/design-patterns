package com.wzh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @author wzh
 * @date 2020-06-05 0:31
 */
public class TankDynamicDemo implements Moveable{
    @Override
    public void move() {
        System.out.println("Tank moving cla....");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TankDynamicDemo demo=new TankDynamicDemo();

        Moveable m= (Moveable) Proxy.newProxyInstance(TankDynamicDemo.class.getClassLoader()
                , new Class[]{Moveable.class},
                new logHandler(demo));
        m.move();
    }
}

class logHandler implements InvocationHandler{

    TankDynamicDemo demo;

    public logHandler(TankDynamicDemo demo) {
        this.demo = demo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method "+method.getName()+" start ..");
        Object o=method.invoke(demo,args);
        System.out.println("method "+method.getName()+" end ..");

        return o;
    }
}

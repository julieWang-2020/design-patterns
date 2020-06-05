package com.wzh.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author wzh
 * @date 2020-06-05 0:31
 */
public class TankDynamicCjlibDemo {

    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new TimeMethodInterceptor());
        Tank tank= (Tank) enhancer.create();
        tank.move();
    }
}

class TimeMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("method "+method.getName()+" start ..");
        Object result=methodProxy.invokeSuper(o,objects);
        System.out.println("method "+method.getName()+" end ..");
        return result;
    }
}

class Tank{
    public void move() {
        System.out.println("Tank moving cla....");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
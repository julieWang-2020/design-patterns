package com.wzh.proxy;

import java.util.Random;

/**
 * @author wzh
 * @date 2020-06-05 0:10
 */
public class TankStaticDemo implements Moveable{
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
        new TankLogProxy(new TankTimeProxy(new TankStaticDemo())).move();
    }
}


class TankTimeProxy implements Moveable{
    public TankTimeProxy(Moveable move) {
        this.move = move;
    }

    Moveable move;

    @Override
    public void move() {
        long start=System.currentTimeMillis();
        move.move();
        long end=System.currentTimeMillis();
        System.out.println("tank moving time:"+(end-start));
    }
}

class TankLogProxy implements Moveable{

    public TankLogProxy(Moveable move) {
        this.move = move;
    }

    Moveable move;

    @Override
    public void move() {
        System.out.println("tank move start");
        move.move();
        long end=System.currentTimeMillis();
        System.out.println("tank move end");
    }
}

interface Moveable{
    void move();
}

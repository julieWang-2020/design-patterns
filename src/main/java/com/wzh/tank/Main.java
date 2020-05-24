package com.wzh.tank;

import com.wzh.tank.vo.Dir;
import com.wzh.tank.vo.Group;
import com.wzh.tank.vo.Tank;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) throws InterruptedException {

        TankFrame tf=new TankFrame();
        for(int i=0;i<5;i++){
            int j =(int) (Math.random()*(4));
            tf.getEnemyTanks().add(new Tank(50+i*80,200,  Dir.values()[j], Group.BAD,tf));
        }
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}

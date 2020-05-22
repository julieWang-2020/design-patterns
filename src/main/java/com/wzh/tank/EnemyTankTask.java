package com.wzh.tank;

import com.wzh.tank.vo.Dir;
import com.wzh.tank.vo.Tank;

public class EnemyTankTask implements Runnable {
    private TankFrame tf;

    public EnemyTankTask(TankFrame tf){
        this.tf=tf;
    }

    @Override
    public void run() {
        int i=(int) (Math.random()*(4));
        System.out.println("==========="+i);
        Tank enemyTank=new Tank(TankFrame.GAME_WIDTH/2,Tank.HEIGHT, Dir.values()[i],tf,false);
        tf.getEnemyTanks().add(enemyTank);
    }

}

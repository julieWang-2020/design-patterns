package com.wzh.tank.fire;

import com.wzh.tank.abstractfactory.BaseTank;
import com.wzh.tank.vo.Bullet;
import com.wzh.tank.vo.Tank;

/**
 * @author wzh
 * @date 2020-05-25 22:50
 */
public class DoubleFireStrategy implements FireStrategy {
    private DoubleFireStrategy(){}


    private static final FireStrategy instance;

    static {
        instance=new DoubleFireStrategy();
        System.out.println("DoubleFireStrategy======"+instance.hashCode());
    }

    public static FireStrategy getInstance() {
        return instance;
    }

    @Override
    public void fire(BaseTank tank) {
        int bx=tank.getX()+ (Tank.WIDTH - Bullet.WIDTH) / 2;
        int by=tank.getY()+ (Tank.HEIGHT- Bullet.HEIGHT) / 2;
        tank.getTf().getGameFactory().createBullet(bx,by,tank.getDir(),tank.getGroup(),tank.getTf());
        tank.getTf().getGameFactory().createBullet(bx+3,by+3,tank.getDir(),tank.getGroup(),tank.getTf());
    }
}

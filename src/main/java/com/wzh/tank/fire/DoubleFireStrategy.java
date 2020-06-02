package com.wzh.tank.fire;

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
    public void fire(Tank tank) {
        int bx=tank.getX()+ (tank.WIDTH - Bullet.WIDTH) / 2;
        int by=tank.getY()+ (tank.HEIGHT- Bullet.HEIGHT) / 2;
        new Bullet(bx,by,tank.getDir(),tank.getGroup(),tank.getGm());
        new Bullet(bx+3,by+3,tank.getDir(),tank.getGroup(),tank.getGm());
    }
}

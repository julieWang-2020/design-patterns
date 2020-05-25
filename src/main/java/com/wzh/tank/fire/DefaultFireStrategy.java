package com.wzh.tank.fire;

import com.wzh.tank.vo.Bullet;
import com.wzh.tank.vo.Tank;

/**
 * @author wzh
 * @date 2020-05-25 22:50
 */
public class DefaultFireStrategy implements FireStrategy {

    private DefaultFireStrategy(){}

    private static final FireStrategy instance;

    static {
        instance=new DefaultFireStrategy();
        System.out.println("DefaultFireStrategy======"+instance.hashCode());
    }


    public static FireStrategy getInstance() {
        return instance;
    }

    @Override
    public void fire(Tank tank) {
        int offsetX = (tank.WIDTH - Bullet.WIDTH) / 2;
        int offsetY = (tank.HEIGHT- Bullet.HEIGHT) / 2;
        int bx=tank.getX()+offsetX,by=tank.getY()+offsetY;
        new Bullet(bx,by,tank.getDir(),tank.getGroup(),tank.getTf());
    }

}

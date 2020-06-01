package com.wzh.tank.abstractfactory;

import com.wzh.tank.TankFrame;
import com.wzh.tank.vo.*;

/**
 * @author wzh
 * @date 2020-06-01 23:54
 */
public class DefaultFactory implements GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame frame) {
        return new Tank(x,y,dir,group,frame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame frame) {
        return new Bullet(x,y,dir,group,frame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame frame) {
        return new Explode(x,y,frame);
    }
}

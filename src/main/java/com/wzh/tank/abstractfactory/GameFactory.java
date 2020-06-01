package com.wzh.tank.abstractfactory;

import com.wzh.tank.TankFrame;
import com.wzh.tank.vo.Dir;
import com.wzh.tank.vo.Group;

/**
 * @author wzh
 * @date 2020-06-01 23:54
 */
public interface GameFactory {

    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame frame);

    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame frame);

    public BaseExplode createExplode(int x, int y, TankFrame frame);

}

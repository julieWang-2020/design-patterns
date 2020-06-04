package com.wzh.tank;

import com.wzh.tank.conf.ProptertyMgr;

/**
 * @author wzh
 * @date 2020-06-04 21:14
 */
public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent e) {
        e.getSource().fire(ProptertyMgr.getString("defaultFS"));
    }
}

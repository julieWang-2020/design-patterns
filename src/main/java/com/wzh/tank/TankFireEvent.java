package com.wzh.tank;

import com.wzh.tank.vo.Tank;

/**
 * @author wzh
 * @date 2020-06-04 21:06
 */
public class TankFireEvent {

    private Tank tank;

    public Tank getSource() {
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}

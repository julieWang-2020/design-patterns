package com.wzh.tank;

import java.io.Serializable;

/**
 * @author wzh
 * @date 2020-06-04 21:06
 */
public interface TankFireObserver extends Serializable {

    public void actionOnFire(TankFireEvent e);
}

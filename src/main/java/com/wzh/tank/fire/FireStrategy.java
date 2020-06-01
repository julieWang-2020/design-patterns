package com.wzh.tank.fire;

import com.wzh.tank.abstractfactory.BaseTank;

/**
 * @author wzh
 * @date 2020-05-25 22:49
 */
public interface FireStrategy {

    public void fire(BaseTank tank);
}

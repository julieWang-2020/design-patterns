package com.wzh.tank.abstractfactory;

import java.awt.*;

/**
 * @author wzh
 * @date 2020-06-01 23:54
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g) ;

    public abstract void collideWith(BaseTank tank);
}

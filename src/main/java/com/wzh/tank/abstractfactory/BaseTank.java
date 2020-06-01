package com.wzh.tank.abstractfactory;

import com.wzh.tank.TankFrame;
import com.wzh.tank.vo.Dir;
import com.wzh.tank.vo.Group;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author wzh
 * @date 2020-06-01 23:54
 */
@Setter
@Getter
public abstract class BaseTank {

    protected int x;
    protected int y;

    private Dir dir = Dir.DOWN;

    private TankFrame tf;

    private Rectangle rect;

    private Group group;

    public abstract void paint(Graphics g) ;

    public abstract void die();
}

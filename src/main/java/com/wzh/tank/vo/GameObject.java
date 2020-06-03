package com.wzh.tank.vo;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author wzh
 * @date 2020-06-02 22:19
 */

public abstract class GameObject {

    @Getter
    @Setter
    protected int x,y;

    public abstract void paint(Graphics g) ;

    public abstract int getWidth();
    public abstract int getHeight();
}

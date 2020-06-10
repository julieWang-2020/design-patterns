package com.wzh.tank.vo;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

/**
 * @author wzh
 * @date 2020-06-02 22:19
 */

public abstract class GameObject implements Serializable {

    @Getter
    @Setter
    protected int x,y;

    public abstract void paint(Graphics g) ;

    public abstract int getWidth();
    public abstract int getHeight();
}

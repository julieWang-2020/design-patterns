package com.wzh.tank.decorator;

import com.wzh.tank.vo.GameObject;

import java.awt.*;

/**
 * @author wzh
 * @date 2020-06-03 22:51
 */
public class RectDecorator extends GODecorator {


    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x=go.getX();
        this.y=go.getY();
        go.paint(g);

        Color c=g.getColor();
        g.setColor(Color.RED);
        g.drawRect(super.go.getX(),super.go.getY(),super.go.getWidth()+2,super.go.getHeight()+2);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}

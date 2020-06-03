package com.wzh.tank.vo;

import com.wzh.tank.GameModel;
import com.wzh.tank.ResourceMgr;

import java.awt.*;


public class Explode extends GameObject{
    public static final int WIDTH= ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT=ResourceMgr.explodes[0].getHeight();

    private int step=0;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void paint(Graphics g) {
        if(step>=ResourceMgr.explodes.length) GameModel.getInstance().remove(this) ;

        else g.drawImage(ResourceMgr.explodes[step++],x,y,null);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
    public int getStep() {
        return step;
    }

}

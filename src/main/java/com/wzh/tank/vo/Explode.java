package com.wzh.tank.vo;

import com.wzh.tank.GameModel;
import com.wzh.tank.ResourceMgr;

import java.awt.*;


public class Explode {
    public static final int WIDTH= ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT=ResourceMgr.explodes[0].getHeight();

    private int x,y;
    private GameModel gm;
    private int step=0;
    public Explode(int x, int y,GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm=gm;
    }
    public void paint(Graphics g) {
        if(step>=ResourceMgr.explodes.length) gm.getExplodes().remove(this) ;

        else g.drawImage(ResourceMgr.explodes[step++],x,y,null);
    }

    public int getStep() {
        return step;
    }

}

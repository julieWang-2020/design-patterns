package com.wzh.tank.vo;

import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import com.wzh.tank.abstractfactory.BaseExplode;

import java.awt.*;


public class Explode extends BaseExplode {
    public static final int WIDTH= ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT=ResourceMgr.explodes[0].getHeight();

    private int x,y;
    private TankFrame tf;

    private int step=0;
    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf=tf;
    }

    @Override
    public void paint(Graphics g) {
        if(step>=ResourceMgr.explodes.length) tf.getExplodes().remove(this) ;

        else g.drawImage(ResourceMgr.explodes[step++],x,y,null);
    }

    public int getStep() {
        return step;
    }

}

package com.wzh.tank.vo;

import com.wzh.tank.GameModel;
import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import lombok.Data;

import java.awt.*;
@Data
public class Bullet extends GameObject {

    private static final int SPEED=10;
    public static final int WIDTH=ResourceMgr.bulletD.getWidth(),HEIGHT=ResourceMgr.bulletD.getHeight();

    Rectangle rect;
    private Dir dir;
    private boolean living=true;
    private Group group;
    private GameModel gm;

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm=gm;
        rect=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        gm.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if(!living) gm.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }

        rect.x=this.x;
        rect.y=this.y;
        if(x<0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) this.living=false;
    }

    public void die() {
        this.living=false;
    }
}

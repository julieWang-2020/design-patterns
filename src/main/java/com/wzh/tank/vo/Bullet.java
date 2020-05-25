package com.wzh.tank.vo;

import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import lombok.Data;

import java.awt.*;
@Data
public class Bullet {

    private static final int SPEED=10;
    public static final int WIDTH=ResourceMgr.bulletD.getWidth(),HEIGHT=ResourceMgr.bulletD.getHeight();

    Rectangle rect;
    private int x,y;
    private Dir dir;
    private boolean living=true;
    private TankFrame tf;
    private Group group;

    public Bullet(int x, int y, Dir dir, Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf=tf;
        rect=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        tf.getBullets().add(this);
    }

    public void paint(Graphics g) {
        if(!living) tf.getBullets().remove(this);
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

    public void collideWith(Tank tank) {
        if(tank.getGroup()==this.group) return;
        if(rect.intersects(tank.getRect())){
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living=false;
    }
}

package com.wzh.tank.vo;

import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import lombok.Data;

import java.awt.*;

/**
 * @author wzh
 * @date 2020-05-20 23:18
 */
@Data
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private boolean isMain=false;

    private static final int SPEED=2;

    public static final int WIDTH=ResourceMgr.tankD.getWidth(),HEIGHT=ResourceMgr.tankD.getHeight();
    private boolean moving=false;

    private boolean live=true;

    private TankFrame tf;

    public Tank(int x, int y, Dir dir,TankFrame tf,boolean isMain) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
        this.isMain=isMain;
    }

    public void paint(Graphics g) {
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
        if(!moving && isMain) return;
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
        if(x<0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            live=false;
    }

    public void fire() {
        int offsetX = (WIDTH - Bullet.WIDTH) / 2;
        int offsetY = (HEIGHT- Bullet.HEIGHT) / 2;
        int bx=this.x+offsetX,by=this.y+offsetY;
//        System.out.println("TankX:"+x+",TankY:"+y+",BulletX:"+bx+",BulletY:"+by+",offsetX:"+WIDTH +","+Bullet.WIDTH+",offsetY:"+offsetY);
        tf.getBullets().add(new Bullet(bx,by,this.dir,tf));
    }
}

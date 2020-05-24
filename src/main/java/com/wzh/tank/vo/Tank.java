package com.wzh.tank.vo;

import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @author wzh
 * @date 2020-05-20 23:18
 */
@Data
public class Tank {
    private static final int SPEED=1;
    public static final int WIDTH=ResourceMgr.goodTankD.getWidth(),HEIGHT=ResourceMgr.goodTankD.getHeight();

    private int x,y;
    private Dir dir = Dir.DOWN;
    private boolean moving=true;
    private boolean living=true;
    private Random random=new Random();
    private TankFrame tf;
    private Group group;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf=tf;
    }

    public void paint(Graphics g) {
        if(!living) tf.getEnemyTanks().remove(this);

        checkBoundary();

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD,x,y,null);
                break;
        }
        move();
    }

    private void checkBoundary() {
        switch (dir){
            case LEFT:
                if(x < 0) this.dir=Dir.RIGHT;
                break;
            case UP:
                if(y < 0) this.dir=Dir.DOWN;
                break;
            case RIGHT:
                if(x >= (TankFrame.GAME_WIDTH - WIDTH)) this.dir=Dir.LEFT;
                break;
            case DOWN:
                if(y >= (TankFrame.GAME_HEIGHT - HEIGHT)) this.dir=Dir.UP;
                break;
        }

    }

    private void move() {
        if(!moving) return;
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

        if(this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if(this.group == Group.BAD && random.nextInt(100)> 95)
            randomDir();
    }

    private void randomDir(){
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int offsetX = (WIDTH - Bullet.WIDTH) / 2;
        int offsetY = (HEIGHT- Bullet.HEIGHT) / 2;
        int bx=this.x+offsetX,by=this.y+offsetY;
//        System.out.println("TankX:"+x+",TankY:"+y+",BulletX:"+bx+",BulletY:"+by+",offsetX:"+WIDTH +","+Bullet.WIDTH+",offsetY:"+offsetY);
        tf.getBullets().add(new Bullet(bx,by,this.dir,group,tf));
    }

    public void die() {
        this.living=false;
        // die 的同时增加爆炸效果
        int offsetX = this.x+(WIDTH - Explode.WIDTH) / 2;
        int offsetY = this.y+(HEIGHT- Explode.HEIGHT) / 2;
        tf.getExplodes().add(new Explode(offsetX,offsetY,tf));
    }
}

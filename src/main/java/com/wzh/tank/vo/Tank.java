package com.wzh.tank.vo;

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
    private static final int SPEED=10;

    private boolean moving=false;

    private TankFrame tf;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }

    public void paint(Graphics g) {
        System.out.println("paint");
        Color c=g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
        move();
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
    }

    public void fire() {
        tf.setBullet(new Bullet(this.x,this.y,this.dir));
    }
}

package com.wzh.tank.vo;

import com.wzh.tank.GameModel;
import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import com.wzh.tank.conf.ProptertyMgr;
import com.wzh.tank.fire.FireStrategy;
import com.wzh.tank.fire.FireStrategyFactory;
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
    private Group group;
    private Rectangle rect;

    private GameModel gm;

    public Tank(int x, int y, Dir dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm=gameModel;
        rect=new Rectangle(this.x,this.y,this.WIDTH,this.HEIGHT);;
    }

    public void paint(Graphics g) {
        if(!living) gm.getEnemyTanks().remove(this);

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
            this.fire(ProptertyMgr.getString("defaultFS"));

        if(this.group == Group.BAD && random.nextInt(100)> 95)
            randomDir();

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    public void fire(String strategyPath) {
        FireStrategy strategy= FireStrategyFactory.getInstance(strategyPath);
//        System.out.println(strategy+":"+strategy.hashCode());
        strategy.fire(this);
    }

    private void boundsCheck() {
        if(x < 0) this.x=28;
        if(y < 0) this.y=15;
        if(x >= TankFrame.GAME_WIDTH ) this.x=TankFrame.GAME_WIDTH - WIDTH;
        if(y >= TankFrame.GAME_HEIGHT ) this.y=TankFrame.GAME_HEIGHT - HEIGHT;

    }

    private void randomDir(){
        this.dir=Dir.values()[random.nextInt(4)];
    }


    public void die() {
        this.living=false;
        // die 的同时增加爆炸效果
        int offsetX = this.x+(WIDTH - Explode.WIDTH) / 2;
        int offsetY = this.y+(HEIGHT- Explode.HEIGHT) / 2;
        gm.getExplodes().add(new Explode(offsetX,offsetY,gm));
    }

}

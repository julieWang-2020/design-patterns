package com.wzh.tank.vo;

import com.wzh.tank.ResourceMgr;
import com.wzh.tank.TankFrame;
import com.wzh.tank.abstractfactory.BaseTank;
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
public class Tank extends BaseTank {
    private static final int SPEED=1;
    public static final int WIDTH=ResourceMgr.goodTankD.getWidth(),HEIGHT=ResourceMgr.goodTankD.getHeight();



    private boolean moving=true;
    private boolean living=true;
    private Random random=new Random();


    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.setDir(dir);
        this.setGroup(group);
        this.setTf(tf);
        this.setRect(new Rectangle(this.x,this.y,this.WIDTH,this.HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        if(!living) this.getTf().getEnemyTanks().remove(this);

        switch (this.getDir()){
            case LEFT:
                g.drawImage(this.getGroup() == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.getGroup() == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.getGroup() == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.getGroup() == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
        if(!moving) return;
        switch (this.getDir()){
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


        if(this.getGroup() == Group.BAD && random.nextInt(100) > 95)
            this.fire(ProptertyMgr.getString("defaultFS"));

        if(this.getGroup() == Group.BAD && random.nextInt(100)> 95)
            randomDir();

        boundsCheck();

        this.getRect().x = this.x;
        this.getRect().y = this.y;
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
        this.setDir(Dir.values()[random.nextInt(4)]);
    }


    @Override
    public void die() {
        this.living=false;
        // die 的同时增加爆炸效果
        int offsetX = this.x+(WIDTH - Explode.WIDTH) / 2;
        int offsetY = this.y+(HEIGHT- Explode.HEIGHT) / 2;
        this.getTf().getExplodes().add(this.getTf().getGameFactory().createExplode(offsetX,offsetY,this.getTf()));
    }

}

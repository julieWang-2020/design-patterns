package com.wzh.tank;

import com.wzh.tank.conf.ProptertyMgr;
import com.wzh.tank.vo.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-02 21:56
 */
public class GameModel {


    Tank mainTank=new Tank(200,400, Dir.DOWN, Group.GOOD,this);
    List<Bullet> bullets=new ArrayList<>();
    List<Tank> enemyTanks=new ArrayList<>();
    List<Explode> explodes=new ArrayList<>();


    public GameModel(){
        int initTankCount= ProptertyMgr.getInt("initTankCount");
        for(int i=0;i<initTankCount;i++){
            int j =(int) (Math.random()*(4));
            getEnemyTanks().add(new Tank(50+i*80,200,  Dir.values()[j], Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("敌机数量："+enemyTanks.size(),16,60);
        g.drawString("子弹数量："+bullets.size(),16,80);
        g.setColor(c);

        mainTank.paint(g);
        for(int i=0;i<enemyTanks.size();i++){
            enemyTanks.get(i).paint(g);
        }
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }

        for(int i=0;i<explodes.size(); i++){
            explodes.get(i).paint(g);
        }
//        for(Iterator<Bullet> it=bullets.iterator();it.hasNext();){
//            Bullet bullet=it.next();
//            if(!bullet.isLiving()) it.remove();
//            else bullet.paint(g);
//        }

        // 碰撞检测
        for(int i=0;i<bullets.size(); i++){
            Bullet b=bullets.get(i);
            for(int j=0;j<enemyTanks.size();j++){
                b.collideWith(enemyTanks.get(j));
            }
        }

    }


    public Tank getMainTank() {
        return mainTank;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }


}

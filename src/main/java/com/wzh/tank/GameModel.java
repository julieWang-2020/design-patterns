package com.wzh.tank;

import com.wzh.tank.conf.ProptertyMgr;
import com.wzh.tank.cor.ColliderChain;
import com.wzh.tank.vo.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-02 21:56
 */
public class GameModel implements Serializable {

    private GameModel(){ }

    private static final GameModel instance=new GameModel();

    public static GameModel getInstance() {
        return instance;
    }

    static {
        instance.init();
    }

    Tank mainTank=null;
    private void init(){
        mainTank= new Tank(200,400, Dir.DOWN, Group.GOOD);

        add(new Wall(TankFrame.GAME_WIDTH/2,0,10,TankFrame.GAME_HEIGHT));
        int initTankCount= ProptertyMgr.getInt("initTankCount");
        for(int i=0;i<initTankCount;i++){
            new Tank(50+i*80,200,  Dir.DOWN, Group.BAD);
        }
    }

    List<GameObject> objects=new ArrayList<>();

    ColliderChain colliderChain=new ColliderChain();




    public void paint(Graphics g) {
//        Color c=g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("敌机数量："+enemyTanks.size(),16,60);
//        g.drawString("子弹数量："+bullets.size(),16,80);
//        g.setColor(c);

        mainTank.paint(g);
        for(int i=0;i<objects.size();i++){
            objects.get(i).paint(g);
        }
//        for(int i=0;i<bullets.size();i++){
//            bullets.get(i).paint(g);
//        }
//
//        for(int i=0;i<explodes.size(); i++){
//            explodes.get(i).paint(g);
//        }
//        for(Iterator<Bullet> it=bullets.iterator();it.hasNext();){
//            Bullet bullet=it.next();
//            if(!bullet.isLiving()) it.remove();
//            else bullet.paint(g);
//        }

//         碰撞检测
        for(int i=0;i<objects.size(); i++){
            GameObject o1=objects.get(i);
            for(int j=i+1;j<objects.size();j++){
                GameObject o2=objects.get(j);
                colliderChain.collider(o1,o2);
            }
        }

    }


    public Tank getMainTank() {
        return mainTank;
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void memento() {
        ObjectOutputStream oops=null;
        try {
            File file=new File("E:\\workspace\\gitspace\\design-patterns\\memento\\tank.t");
            oops = new ObjectOutputStream(new FileOutputStream(file));
            oops.writeObject(mainTank);
            oops.writeObject(objects);
            oops.writeObject(colliderChain);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(oops!=null) {
                try {
                    oops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void rollback() {
        ObjectInputStream oips=null;
        try {
            File file=new File("E:\\workspace\\gitspace\\design-patterns\\memento\\tank.t");
            oips = new ObjectInputStream(new FileInputStream(file));
            Tank mainTank= (Tank) oips.readObject();
            List<GameObject> objects= (List<GameObject>) oips.readObject();
            ColliderChain colliderChain= (ColliderChain) oips.readObject();

            this.mainTank=mainTank;
            this.objects=objects;
            this.colliderChain=colliderChain;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(oips!=null) {
                try {
                    oips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public List<Bullet> getBullets() {
//        return bullets;
//    }
//
//    public List<Tank> getEnemyTanks() {
//        return enemyTanks;
//    }
//
//    public List<Explode> getExplodes() {
//        return explodes;
//    }


}

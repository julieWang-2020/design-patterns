package com.wzh.tank;

import com.wzh.tank.abstractfactory.*;
import com.wzh.tank.conf.ProptertyMgr;
import com.wzh.tank.vo.Dir;
import com.wzh.tank.vo.Group;
import com.wzh.tank.vo.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-05-20 21:51
 */
public class TankFrame extends Frame {

    Tank mainTank=new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    List<BaseBullet> bullets=new ArrayList<>();
    List<BaseTank> enemyTanks=new ArrayList<>();
    List<BaseExplode> explodes=new ArrayList<>();

    GameFactory gameFactory=new DefaultFactory();
    Image offScreenImage=null;

    public static final int GAME_WIDTH= ProptertyMgr.getInt("tankGameWidth");
    public static final int GAME_HEIGHT=ProptertyMgr.getInt("tankGameHeight");
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle(ProptertyMgr.getString("tankGameTitle"));
        setVisible(true);

        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

//        addEnemyTask();
    }

    @Override
    public void update(Graphics g) {
        // 双缓冲消除闪烁
        // 两支画笔 g 系统的画笔， gOffScreen 内存画笔
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen=offScreenImage.getGraphics();
        Color c=gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_WIDTH);
        gOffScreen.setColor(c);
        print(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
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
            BaseBullet b= bullets.get(i);
            for(int j=0;j<enemyTanks.size();j++){
                b.collideWith(enemyTanks.get(j));
            }
        }


    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire(ProptertyMgr.getString("defaultFS"));
                    break;
                case KeyEvent.VK_SHIFT:
                    mainTank.fire(ProptertyMgr.getString("doubleFS"));
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD) mainTank.setMoving(false);
            else {
                mainTank.setMoving(true);
                if(bL) mainTank.setDir(Dir.LEFT);
                if(bU) mainTank.setDir(Dir.UP);
                if(bR) mainTank.setDir(Dir.RIGHT);
                if(bD) mainTank.setDir(Dir.DOWN);
            }

        }
    }

    public List<BaseBullet> getBullets() {
        return bullets;
    }

    public List<BaseTank> getEnemyTanks() {
        return enemyTanks;
    }

    public List<BaseExplode> getExplodes() {
        return explodes;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }
}

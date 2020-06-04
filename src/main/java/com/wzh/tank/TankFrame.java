package com.wzh.tank;

import com.wzh.tank.conf.ProptertyMgr;
import com.wzh.tank.vo.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author wzh
 * @date 2020-05-20 21:51
 */
public class TankFrame extends Frame {

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
        GameModel.getInstance().paint(g);
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
                    GameModel.getInstance().getMainTank().handleFireKey();
                    break;
                case KeyEvent.VK_SHIFT:
                    GameModel.getInstance().getMainTank().fire(ProptertyMgr.getString("doubleFS"));
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
            if(!bL && !bU && !bR && !bD) GameModel.getInstance().getMainTank().setMoving(false);
            else {
                GameModel.getInstance().getMainTank().setMoving(true);
                if(bL) GameModel.getInstance().getMainTank().setDir(Dir.LEFT);
                if(bU) GameModel.getInstance().getMainTank().setDir(Dir.UP);
                if(bR) GameModel.getInstance().getMainTank().setDir(Dir.RIGHT);
                if(bD) GameModel.getInstance().getMainTank().setDir(Dir.DOWN);
            }

        }
    }

}

package com.wzh.tank.vo;

import com.wzh.tank.GameModel;
import com.wzh.tank.TankFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author wzh
 * @date 2020-06-02 23:52
 */
@Setter
@Getter
public class Wall extends GameObject {

    public static final int WIDTH=10;
    private GameModel gm;
    private Rectangle rect;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        rect=new Rectangle(x,y,WIDTH, TankFrame.GAME_HEIGHT);

    }

    @Override
    public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.CYAN);
        g.fillRect(x,y,WIDTH, TankFrame.GAME_HEIGHT);
        g.setColor(c);
    }
}

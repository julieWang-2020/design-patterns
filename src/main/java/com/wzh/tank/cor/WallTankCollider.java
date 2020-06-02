package com.wzh.tank.cor;

import com.wzh.tank.vo.Bullet;
import com.wzh.tank.vo.GameObject;
import com.wzh.tank.vo.Tank;
import com.wzh.tank.vo.Wall;

/**
 * @author wzh
 * @date 2020-06-02 22:41
 */
public class WallTankCollider implements Collider {

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Tank){
            Wall w= (Wall) o1;
            Tank t= (Tank) o2;

            if(w.getRect().intersects(t.getRect())){
                t.stop();
            }
        }
        if(o2 instanceof Bullet && o1 instanceof Tank){
            collider(o2,o1);
        }

        return true;
    }
}

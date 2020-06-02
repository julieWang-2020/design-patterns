package com.wzh.tank.cor;

import com.wzh.tank.vo.Bullet;
import com.wzh.tank.vo.GameObject;
import com.wzh.tank.vo.Tank;

/**
 * @author wzh
 * @date 2020-06-02 22:41
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b= (Bullet) o1;
            Tank t= (Tank) o2;
            if(t.getGroup()==b.getGroup()) return true;

            if(b.getRect().intersects(t.getRect())){
                t.die();
                b.die();
                return false;
            }
        }
        if(o2 instanceof Bullet && o1 instanceof Tank){
            return collider(o2,o1);
        }

        return true;
    }
}

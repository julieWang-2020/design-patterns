package com.wzh.tank.cor;

import com.wzh.tank.vo.GameObject;
import com.wzh.tank.vo.Tank;

/**
 * @author wzh
 * @date 2020-06-02 22:41
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1= (Tank) o1;
            Tank t2= (Tank) o2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.stop();
            }
        }
        return true;
    }
}

package com.wzh.tank.cor;

import com.wzh.tank.vo.GameObject;

/**
 * @author wzh
 * @date 2020-06-02 22:38
 */
public interface Collider {

    boolean collider(GameObject o1,GameObject o2);
}

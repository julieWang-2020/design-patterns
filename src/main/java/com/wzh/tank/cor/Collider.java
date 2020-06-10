package com.wzh.tank.cor;

import com.wzh.tank.vo.GameObject;

import java.io.Serializable;

/**
 * @author wzh
 * @date 2020-06-02 22:38
 */
public interface Collider  extends Serializable {

    boolean collider(GameObject o1,GameObject o2);
}

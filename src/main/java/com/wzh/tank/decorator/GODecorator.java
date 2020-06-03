package com.wzh.tank.decorator;

import com.wzh.tank.vo.GameObject;

/**
 * @author wzh
 * @date 2020-06-03 22:51
 */
public abstract  class GODecorator extends GameObject {

    protected GameObject go;

    public GODecorator(GameObject go){
        this.go=go;
    }


}

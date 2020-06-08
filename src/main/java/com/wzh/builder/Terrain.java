package com.wzh.builder;

import lombok.Data;

/**
 * @author wzh
 * @date 2020-06-08 21:43
 */
public class Terrain {

    public Wall w;
    public Fort f;
    public Mine m;



}

@Data
class Wall{
    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    private int x,y,w,h;

}

@Data
class Fort{
    public Fort(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    private int x,y,w,h;
}
@Data
class Mine{
    public Mine(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    private int x,y,w,h;
}

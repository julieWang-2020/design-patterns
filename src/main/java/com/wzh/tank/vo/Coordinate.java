package com.wzh.tank.vo;

import lombok.Data;

/**
 * @author wzh
 * @date 2020-05-25 7:47
 */
@Data
public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

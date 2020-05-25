package com.wzh.stragegy;

import lombok.Data;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-05-25 15:19
 **/
@Data
public class Cat implements Comparable <Cat>{

    private double weight;

    public Cat(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Cat o) {
        if(this.weight < o.getWeight()) return -1;
        else if(this.weight > o.getWeight()) return 1;
        else return 0;
    }
}

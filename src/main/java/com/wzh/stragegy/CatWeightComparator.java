package com.wzh.stragegy;

import java.util.Comparator;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-05-25 15:26
 **/
public class CatWeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.getWeight() < o2.getWeight()) return -1;
        else if(o1.getWeight() > o2.getWeight()) return 1;
        else return 0;
    }
}

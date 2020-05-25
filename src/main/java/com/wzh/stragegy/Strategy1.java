package com.wzh.stragegy;

import java.util.Arrays;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-05-25 15:01
 **/
public class Strategy1 {

    public static void main(String[] args) {
        int arr[]={3,1,9,6,4,7,5};
        Sorter.sort(arr);
        System.out.println(Arrays.toString(arr));

        Cat catArr[]={new Cat(90),new Cat(20),new Cat(40),new Cat(15)};
        Sorter.sort(catArr);
        System.out.println(Arrays.toString(catArr));

        Cat catArr1[]={new Cat(70),new Cat(100),new Cat(40),new Cat(15)};
        CatWeightComparator comparator=new CatWeightComparator();
        Arrays.sort(catArr1,comparator);
        System.out.println(Arrays.toString(catArr1));


        Cat catArr2[]={new Cat(70),new Cat(100),new Cat(40),new Cat(15)};
        Arrays.sort(catArr2,(o1,o2) ->{
            if(o1.getWeight() > o2.getWeight()) return -1;
            else if(o1.getWeight() < o2.getWeight()) return 1;
            else return 0;
        });
        System.out.println(Arrays.toString(catArr2));

    }
}

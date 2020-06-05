package com.wzh.iterator;

/**
 * @author wzh
 * @date 2020-06-05 20:46
 */
public class IteratorMain {

    public static void main(String[] args) {
        Collection_ list=new ArrayList_();
        for(int i=0;i<15;i++){
            list.add(("s"+i));
        }

        System.out.println(list.size());

        Iterator_ it=list.iterator();
        while (it.hasNext()){
            Object o=it.next();
            System.out.println(o);
        }
    }
}

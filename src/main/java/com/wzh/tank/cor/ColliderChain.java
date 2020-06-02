package com.wzh.tank.cor;

import com.wzh.tank.conf.ProptertyMgr;
import com.wzh.tank.vo.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-02 22:38
 */
public class ColliderChain implements Collider{

    // 动态分配空间
    private List<Collider> colliders=new LinkedList<>();

    public ColliderChain(){
        String[] pathArr=ProptertyMgr.getString("colliders").split(",");
        for(String path:pathArr){
            try {
                add((Collider) Class.forName(path).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Collider c){
        colliders.add(c);
    }

    public boolean collider(GameObject o1, GameObject o2){
        for(int i=0;i<colliders.size();i++){
            if(!colliders.get(i).collider(o1,o2)) return false;
        }
        return true;
    }
}

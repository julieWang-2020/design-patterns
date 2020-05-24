package com.wzh.tank.conf;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wzh
 * @date 2020-05-24 23:10
 */
public class ProptertyMgr {

    static Properties props =new Properties();
    static {
        try {
            props.load(ProptertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(props == null) return null;
        return props.get(key);
    }
}

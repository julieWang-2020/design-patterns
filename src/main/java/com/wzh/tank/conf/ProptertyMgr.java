package com.wzh.tank.conf;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wzh
 * @date 2020-05-24 23:10
 */
public class ProptertyMgr {

    private ProptertyMgr(){}

    private static Properties props =new Properties();
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
    public static String getString(String key){
        Object obj=get(key);
        if(obj == null) return null;
        return obj.toString();
    }

    public static int getInt(String key){
        String str=getString(key);
        if(StringUtils.isEmpty(str)) return 0;

        return Integer.parseInt(str);
    }

}

package com.wzh.tank.fire;

/**
 * @author wzh
 * @date 2020-05-25 22:49
 */
public class FireStrategyFactory {

    private FireStrategyFactory(){}

    public static FireStrategy getInstance(String strategyPath){
        if(strategyPath.equals(DefaultFireStrategy.class.getName())){
            return DefaultFireStrategy.getInstance();
        }
        if(strategyPath.equals(DoubleFireStrategy.class.getName())){
            return DoubleFireStrategy.getInstance();
        }
        return null;
    }
}

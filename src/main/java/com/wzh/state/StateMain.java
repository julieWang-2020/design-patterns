package com.wzh.state;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-06-10 17:38
 **/
public class StateMain {
    public static void main(String[] args) {

        Car car=new Car();
        car.state=CarState.CLOSED;

        car.openDoor();
        car.closeDoor();
        car.runCar();
        car.stopCar();

    }
}

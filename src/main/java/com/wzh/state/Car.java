package com.wzh.state;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-06-10 17:36
 **/
public class Car {

    CarState state;

    public void openDoor(){
        if(state == CarState.OPEN || state==CarState.RUNNING){
            System.out.println(" open the door fail! Wrong state ");
        }else {
            System.out.println("open the door success");
            state=CarState.OPEN;
        }

    }

    public void closeDoor(){
        if(state == CarState.CLOSED){
            System.out.println(" close the door fail! Wrong state ");
        }else {
            System.out.println("close the door success");
            state=CarState.CLOSED;
        }
    }


    public void runCar(){
        if(state == CarState.OPEN) {
            System.out.println(" run the car fail! Wrong state ");
        }else {
            System.out.println("run the car success");
            state=CarState.RUNNING;
        }
    }

    public void stopCar(){
        if(state == CarState.OPEN){
            System.out.println(" stop the car fail! Wrong state ");
        }else {
            System.out.println("stop the car success");
            state=CarState.STOPPED;
        }
    }
}

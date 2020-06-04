package com.wzh.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-04 20:38
 */
public class ObserverMain {

    public static void main(String[] args) {
        Child child=new Child();
        child.observers.add(new Dad());
        child.observers.add(new Mum());
        child.observers.add(new Dog());
        child.wakeUp();
    }
}

class Child{
    boolean cry=false;

    List<Observer> observers=new ArrayList<>();

    public void wakeUp(){
        cry=true;
        WakeUpEvent event=new WakeUpEvent();
        for(Observer o:observers){
            o.actionOnWakeUp(event);
        }
    }

}

abstract class Event<T>{

    abstract  T getSource();
}

class WakeUpEvent extends Event<Child>{

    long timestamp;
    String loc;
    Child source;

    @Override
    public Child getSource() {
        return source;
    }
}

interface Observer{
    void actionOnWakeUp(WakeUpEvent event);
}

class Dad implements Observer{

    public void feed(){
        System.out.println("Dad feeding ...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        feed();
    }
}

class Mum implements Observer{

    public void hug(){
        System.out.println("Mum hugging ...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        hug();
    }
}


class Dog implements Observer{

    public void bark(){
        System.out.println("Dog barking ...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        bark();
    }
}




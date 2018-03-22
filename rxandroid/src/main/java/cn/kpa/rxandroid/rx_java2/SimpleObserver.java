package cn.kpa.rxandroid.rx_java2;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by mr.kong on 2018/2/26.
 *
 */

public class SimpleObserver implements Observer {
    SimpleObserver(SimpleObservable observable) {
        observable.addObserver(this);
    }

    public void update(Observable observable,Object obj){
        System.out.println("data is change  " + ((SimpleObservable) observable).getData());
        //1140 866。5
    }
}


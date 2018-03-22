package cn.kpa.rxandroid.rx_java2;

import java.util.Observable;

/**
 * Created by mr.kong on 2018/2/26.
 * 创建一个被观察者
 */

public class SimpleObservable extends Observable {
    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        if (this.data != data) {
            this.data = data;
            setChanged();//发生改变
            notifyObservers(); //通知状态发生改变
        }
    }
}

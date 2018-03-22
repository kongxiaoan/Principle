package cn.kpa.rxandroid.rx_java2;

/**
 * Created by mr.kong on 2018/2/26.
 */

public class Test {
    public static void main(String[] args){
        SimpleObservable simpleObservable = new SimpleObservable();
        SimpleObserver simpleObserver = new SimpleObserver(simpleObservable);

        simpleObservable.setData(1);
        simpleObservable.setData(3);
        simpleObservable.setData(4);
        simpleObservable.setData(2);

    }
}

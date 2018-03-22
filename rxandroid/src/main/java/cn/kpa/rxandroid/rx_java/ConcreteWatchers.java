package cn.kpa.rxandroid.rx_java;

/**
 * Created by mr.kong on 2018/2/26.
 */

public class ConcreteWatchers implements Watcher {
    @Override
    public void update(String str) {
        System.out.println(str);
    }
}

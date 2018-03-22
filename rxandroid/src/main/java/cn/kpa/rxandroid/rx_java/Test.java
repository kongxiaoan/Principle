package cn.kpa.rxandroid.rx_java;

/**
 * Created by mr.kong on 2018/2/26.
 */

public class Test {
    public static void main(String args[]){
        Watched watched = new ConcreateWatcher();

        Watcher watcher = new ConcreteWatchers();
        Watcher watcher1 = new ConcreteWatchers();
        Watcher watcher2 = new ConcreteWatchers();
        Watcher watcher3 = new ConcreteWatchers();
        Watcher watcher4 = new ConcreteWatchers();

        watched.addWatcher(watcher);
        watched.addWatcher(watcher1);
        watched.addWatcher(watcher2);
        watched.addWatcher(watcher3);
        watched.addWatcher(watcher4);

        watched.notify("发生了事件");
    }
}

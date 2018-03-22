package cn.kpa.rxandroid.rx_java;

/**
 * Created by mr.kong on 2018/2/26.
 */

public interface Watched {
    public void addWatcher(Watcher watcher);

    public void remove(Watcher watcher);

    public void notify(String str);
}

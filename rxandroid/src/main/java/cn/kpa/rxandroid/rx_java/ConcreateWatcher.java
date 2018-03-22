package cn.kpa.rxandroid.rx_java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.kong on 2018/2/26.
 */

public class ConcreateWatcher implements Watched {
    private List<Watcher> list = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void remove(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notify(String str) {
        for (Watcher watcher : list) {
            watcher.update(str);
        }
    }
}

package cn.kpa.rxandroid.utils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by mr.kong on 2018/2/27.
 */

public class DowanloadUtils {
    /**
     * 这中写法不好
     * @param path
     * @param callBack
     * @return
     */
    public static byte[] downloadImage(String path, CallBack callBack) {
        //异步任务在这里编写 本身就是一个内部类 有继承关系 返回结果很难
        return null;
    }

    interface CallBack {
        void callBack(byte[] data);
    }


    public Observable<byte[]> downloadImage(String path){
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if(!subscriber.isUnsubscribed()){

                }
            }
        });
    }
}

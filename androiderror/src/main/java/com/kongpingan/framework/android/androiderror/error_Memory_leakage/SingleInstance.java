package com.kongpingan.framework.android.androiderror.error_Memory_leakage;

import android.content.Context;

/**
 * Created by mr.kong on 2018/3/21.
 */

public class SingleInstance {
    private Context mContext;
    private static SingleInstance singleInstance;

    private SingleInstance(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 会造成内存泄漏
     * 即使使用的对象已经销毁 但是因为它的引用还存在于一个SingleInstance 中 就不可能被GC调
     *
     * @param context
     * @return
     */
    public static SingleInstance getInstance(Context context) {
        if (singleInstance == null) {
            singleInstance = new SingleInstance(context);
        }
        return singleInstance;
    }
}

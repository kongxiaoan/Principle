package com.android.glideprogresstest

import android.os.Looper
import java.lang.IllegalArgumentException

/**
 * Created by mr.kong on 2017/11/22.
 */
class Utils {
    companion object {
        /**
         * 判断是否是主线程
         */
        fun isOnMainThread(): Boolean {
            return Looper.myLooper() == Looper.getMainLooper()
        }

        fun assertMainThread(){
            if(!isOnMainThread()){
                throw  IllegalArgumentException("你必须在祝线程调用此方法 --> You must call this method on the main thread")
            }
        }

    }
}
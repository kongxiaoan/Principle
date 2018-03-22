package cn.kpa.test.thread_handler

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

/**
 * Created by mr.kong on 2018/3/7.
 * 在子线程中使用Handler
 */
class MyThread : Thread() {
    override fun run() {
        super.run()
        Looper.prepare() //为一个线程开启消息循环 默认Android新诞生的线程没有开启消息循环的 （当然主线程是除外的）
        val handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.e("MyThread", Thread.currentThread().name + "    messageWhat   " + msg.what)
            }
        }

        handler.sendEmptyMessage(2)
        Looper.loop()//使得线程进行工作（在这剧代码之后的代码逻辑是不执行的）
        Log.e("MyThread", "执行--------") //是不会执行的
    }

}
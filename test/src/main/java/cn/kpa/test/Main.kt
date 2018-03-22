package cn.kpa.test

import cn.kpa.test.thread_handler.MyThread

/**
 * Created by mr.kong on 2018/3/7.
 *
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        MyThread().start()
    }
}
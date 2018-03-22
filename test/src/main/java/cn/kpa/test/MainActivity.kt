package cn.kpa.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread
import cn.kpa.test.thread_handler.MyThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test1()

        thread_handler.setOnClickListener {
            MyThread().start()
        }
    }

    private fun test1() {

        /**
         * 在kotlin 中 函数和普通的变量都是一级的拥有所有的主权
         * 好处是什么呢？
         *
         */
        fun test2() {

        }
    }
}

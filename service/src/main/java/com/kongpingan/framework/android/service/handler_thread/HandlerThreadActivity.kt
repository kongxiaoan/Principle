package com.kongpingan.framework.android.service.handler_thread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.text.Html
import com.kongpingan.framework.android.service.R
import kotlinx.android.synthetic.main.activity_handler_thread.*

class HandlerThreadActivity : AppCompatActivity() {
    private lateinit var mHandlerThread: HandlerThread
    private lateinit var mCheckMsgHandler: Handler
    private var isUpdateInfo = false
    private val MSG_UPDATE_INFO = 0x110
    //与UI线程管理的Handler
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)

        initBackThread()
    }

    override fun onResume() {
        super.onResume()
        //开始查询
        isUpdateInfo = true
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO)
    }

    override fun onPause() {
        super.onPause()
        //停止查询
        isUpdateInfo = false
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO)
    }

    private fun initBackThread() {

        mHandlerThread = HandlerThread("handler-thread")
        mHandlerThread.start()

        mCheckMsgHandler = object : Handler(mHandlerThread.looper) {
            override fun handleMessage(msg: Message?) {
                checkForUpdate()
                if (isUpdateInfo) {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000)
                }
            }
        }
    }

    private fun checkForUpdate() {

        Thread.sleep(1000)
        mHandler.post(object : Runnable {
            override fun run() {
                var result = "实时更新 <font color='red'>%d</font>"
                result = String.format(result, (Math.random() * 3000 + 1000).toInt())
                id_textview.text = Html.fromHtml(result)
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        mHandlerThread.quit()
    }
}

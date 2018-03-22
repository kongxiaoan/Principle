package com.android.notificationdemo

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


/**
 *
 * 将通知规划到多媒体中
 * 创建通知 不管在什么地方创建的步骤都是相同的
 * 1、NotificationManager 创建对象
 * 2、创建Notification对象
 *
 */
class MainActivity : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var mNotification: NotificationCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setAndroidMoreThan_3_0()
//        setAndroid_3_0_Bwlow()
//        httpClient()
        button.setOnClickListener({
            toast("dfasfasd")
        })

    }

    private fun httpClient() {
    }

    /**
     * kotlin 的还有一个好处就是 不需要写太多的工具类 直接使用扩展方法就OK了
     * 定义的Toast的扩展的方法
     */
    private inline fun MainActivity.toast(message: String) {
        Toast.makeText(this, "", Toast.LENGTH_LONG).show()
    }

    /**
     * 3.0 版本以下
     */
    private fun setAndroid_3_0_Bwlow() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notification = Notification(R.drawable.ic_launcher_background, "通知", System.currentTimeMillis())
        //android 已经抛弃setLatestEventInfo 方法了 完全不兼容
        //notification.setLatestEventInfo(this, "", "", PendingIntent.getActivity(this, 0, Intent(this, ResultActivity::class.java), 0))
        notificationManager.notify(0, notification)
    }

    /**
     * 3.0 版本以上的通知代码
     */
    private fun setAndroidMoreThan_3_0() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotification = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("我是标题")
                .setContentText("我是通知的内容")

        notificationManager.notify(1, mNotification.build())
    }
}

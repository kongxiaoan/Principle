package cn.kpa.remoteviewsdemo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Toast.makeText(this, "8。0", Toast.LENGTH_SHORT).show()
                notificationO()
            } else {
                notification()
            }
        }
    }

    /**
     * 在8.0中 通知栏要设置NotificationChannel
     */
    @SuppressLint("PrivateResource")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationO() {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = "my_channel_01"
        val name: CharSequence = "channel_name"
        val description = "我是描述"
        val importance = NotificationManager.IMPORTANCE_LOW
        val notificationChannel = NotificationChannel(id, name, importance)
        notificationChannel.description = description
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        mNotificationManager.createNotificationChannel(notificationChannel)

        val builder = Notification.Builder(this)
                .setContentTitle("我是标题")
                .setContentText("我是文本")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setChannelId("my_channel_01")
                .build()
        mNotificationManager.notify(1, builder)
    }

    private fun notification() {
        val managerCompat = NotificationManagerCompat.from(this)
        val builder = NotificationCompat.Builder(this)
        builder.setContentTitle("标题")
                .setContentText("我是一个测试内容")
                /**
                Notification.FLAG_SHOW_LIGHTS              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
                Notification.FLAG_ONGOING_EVENT          //发起正在运行事件（活动中）
                Notification.FLAG_INSISTENT   //让声音、振动无限循环，直到用户响应 （取消或者打开）
                Notification.FLAG_ONLY_ALERT_ONCE  //发起Notification后，铃声和震动均只执行一次
                Notification.FLAG_AUTO_CANCEL      //用户单击通知后自动消失
                Notification.FLAG_NO_CLEAR          //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
                Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务
                 */
                .setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击的意图
//                .setNumber(10) // 设置通知集合的数量
                .setTicker("测试的数据来了") // 同值首次出现在通知栏 带上升的动画效果
                .setWhen(System.currentTimeMillis()) //通知产生的时候 会在通知里显示时间 一般是系统获取的时间
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT) //设置该通知的优先级
                .setAutoCancel(true) //设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false) // 设置它为一个正在进行的通知 他们通常是用来表示一个后台的任务，用户积极参与（如音乐播放）或以
                //某种方式正在等待 一次占用设备

                /**
                Notification.DEFAULT_VIBRATE    //添加默认震动提醒  需要 VIBRATE permission
                Notification.DEFAULT_SOUND    // 添加默认声音提醒
                Notification.DEFAULT_LIGHTS// 添加默认三色灯提醒
                Notification.DEFAULT_ALL// 添加默认以上3种全部提醒
                 */
                .setDefaults(Notification.DEFAULT_VIBRATE) //向通知添加声音、闪烁和震动效果
                .setSmallIcon(R.drawable.notification_icon_background)
                .setVibrate(longArrayOf(0, 300, 500, 700)) //设置震动的方式 意思是 延迟0s 震动300 延迟500 震动700
                /**
                 * android 支持三色登提醒 这个方法就是设置在不同的场景下有不同的颜色
                 *arg1: 表示灯光的颜色
                 * arg2:亮持续的时间
                 * arg3:俺的时间
                 *
                 * 但是这个只有在Notification.FLAG_SHOW_LIGHTS 的时候 才会支持三色灯的提醒
                 * 并且这个颜色跟设备有关
                 */
                .setLights(0xff0000ff.toInt(), 300, 0)
//                .setSound() //设置或者自定义铃声
        managerCompat.notify(1, builder.build())
    }

    private fun getDefaultIntent(flaG_AUTO_CANCEL: Int): PendingIntent? {
        return PendingIntent.getActivity(this, 1, Intent(), flaG_AUTO_CANCEL)
    }
}

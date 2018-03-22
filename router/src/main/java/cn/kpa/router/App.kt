package cn.kpa.router

import android.app.Application
import android.support.v4.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by mr.kong on 2018/3/5.
 */
public class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openLog() //打印日志
        ARouter.openDebug() //开启调试（如果在InstanRun模式下运行 必须开启调试模式 线上版本需要关闭）
        ARouter.init(this) //初始化
    }
}
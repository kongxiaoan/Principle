package com.android.hookdemo;

import android.app.Application;

import com.android.hookdemo.hook.HookUtils;

/**
 * Created by mr.kong on 2017/11/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //这个Main2Activity在清单文件中注册过，以后所有的Activitiy都可以用ProxyActivity无需声明，绕过监测
        HookUtils hookAmsUtil = new HookUtils(Main2Activity.class, this);
        hookAmsUtil.hookSystemHandler();
        hookAmsUtil.hookAms();

    }
}

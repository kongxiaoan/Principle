package com.android.hookdemo.java;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by mr.kong on 2017/11/15.
 */

public class HookUtils {
    private Class<?> activity;//代理Activity
    private Context context;
    Object activityThreadVlaue; //系统程序Activity的Thread的入口的对象

    public HookUtils(Class<?> activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void hookAms() {
        Log.i("hook", "start hook");
        //反射的原因 找到IActivityManager的对象
        try {
            Class<?> forName = Class.forName("android.app.ActivityManagerNative");
            Field gDefault = forName.getDeclaredField("gDefault");//
            gDefault.setAccessible(true);//
            //获取字段的值
            Object defaultValue = gDefault.get(null);//gDefault的变量值 --。
            //反射Singleten
            Class<?> name = Class.forName("android.util.Singleton");
            Field instance = name.getDeclaredField("mInstance");
            instance.setAccessible(true);
            //系统的IActivityManager对象
            Object iActivityManagerObject = instance.get(defaultValue);
            //hook
            Class<?> iActivityManagerIntercept = Class.forName("android.app.IActivityManager");
            //动态代理
            AmsIncovationHandler handler = new AmsIncovationHandler(iActivityManagerObject);
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{iActivityManagerIntercept}, handler);
            instance.set(defaultValue, proxyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class AmsIncovationHandler implements InvocationHandler {
        private Object iActivityManagerObject;

        public AmsIncovationHandler(Object iActivityManagerObject) {
            this.iActivityManagerObject = iActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.i("", "method" + method.getName());
            if ("startActivity".contains(method.getName())) {
                Log.i("", "method         Activiy已经启动  ");
                int index = 0;
                Intent intent = null;
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Intent) {
                        //找到了startActivity（）方法
                        intent = (Intent) args[i]; //拦截的原来的intent
                        index = i;//记录的这个意图是不能被启动的
                        break;
                    }
                }
                Intent proxyIntent = new Intent();
                ComponentName componentName = new ComponentName(context, activity);
                proxyIntent.setComponent(componentName);
                proxyIntent.putExtra("old", intent);
                args[index] = proxyIntent;
                return method.invoke(iActivityManagerObject, args);
            }
            return method.invoke(iActivityManagerObject, args);
        }
    }

    public void hookSystemHandler() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            //获取主线程对象
            Object activityThread = currentActivityThreadMethod.invoke(null);
            //获取mH字段
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);
            //获取Handler
            Handler handler = (Handler) mH.get(activityThread);
            //获取原始的mCallBack字段
            Field mCallBack = Handler.class.getDeclaredField("mCallback");
            mCallBack.setAccessible(true);
            //这里设置了我们自己实现了接口的CallBack对象
            mCallBack.set(handler, new ActivityThreadHandlerCallback(handler));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class ActivityThreadHandlerCallback implements Handler.Callback {
        private Handler mHandler;

        public ActivityThreadHandlerCallback(Handler mHandler) {
            this.mHandler = mHandler;
        }

        @Override
        public boolean handleMessage(Message msg) {
            Log.i("HookAmsUtil", "handleMessage");
            //替换之前的Intent
            if (msg.what == 100) {
                Log.i("HookAmsUtil", "lauchActivity");
                handleLauchActivity(msg);
            }

            mHandler.handleMessage(msg);
            return true;


        }

        private void handleLauchActivity(Message msg) {
            Object obj = msg.obj;//ActivityClientRecord
            try {
                Field intentField = obj.getClass().getDeclaredField("intent");
                intentField.setAccessible(true);
                Intent proxyInent = (Intent) intentField.get(obj);
                Intent realIntent = proxyInent.getParcelableExtra("oldIntent");
                if (realIntent != null) {
                    proxyInent.setComponent(realIntent.getComponent());
                }
            } catch (Exception e) {
                Log.i("HookAmsUtil", "lauchActivity falied");
            }


        }
    }

}

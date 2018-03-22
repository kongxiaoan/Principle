package com.android.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * SlidingMenu 的使用 策划菜单
 * <p>
 * SlidingMenu 继承RelativeLayout  对外暴露API 同时添加
 * CustomViewAbove 继承ViewGroup 处理触摸事件
 * CustomViewBehind 继承ViewGroup 用来配置参数，显示侧边栏的Menu部分
 */
public class MainActivity extends AppCompatActivity {

    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置触摸屏幕的模式是全屏触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        //设置策划菜单的视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置渐入渐出的效果
        slidingMenu.setFadeDegree(0.355f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
    }
}

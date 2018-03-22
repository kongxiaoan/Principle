package cn.kpa.router.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.kpa.router.R
import cn.kpa.router.utils.RouterUtils
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * 在支持路由的页面上添加注解（必选）
 * 这里的路径需要注意的是至少需要有两级 ，/xx/xx
 */
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}

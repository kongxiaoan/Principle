package cn.kpa.router.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.kpa.router.R
import cn.kpa.router.utils.RouterUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

private class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener({
            //发起路由跳转
            ARouter.getInstance().build(RouterUtils.Main2Activity).navigation()
        })
    }
}

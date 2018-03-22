package cn.kpa.rxandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity 充当的角色
 * 1、UI主线程 负责绘制
 * 2、开启子线程 获取数据
 * 3、赋值到控件中
 * 4、逻辑判断等等
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        download.setOnClickListener({

        })
    }
}

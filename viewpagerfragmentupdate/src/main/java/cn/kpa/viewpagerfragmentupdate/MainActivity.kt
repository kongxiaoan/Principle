package cn.kpa.viewpagerfragmentupdate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mList: MutableList<String> = arrayListOf()
    private lateinit var mPageView: PageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        text.setOnClickListener {
            mPageView.update()
        }
    }

    private fun initView() {
        fun addList(): MutableList<String> {
            val vList = ArrayList<String>()
            for (i in 0 until 100) {
                vList.add("第 " + i + "个")
            }
            return vList
        }
        mList.addAll(addList())
        mPageView = PageView(this, mList)
        main_viewpage.addView(mPageView)
    }
}

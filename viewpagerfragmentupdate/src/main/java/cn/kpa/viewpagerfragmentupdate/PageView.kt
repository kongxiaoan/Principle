package cn.kpa.viewpagerfragmentupdate

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.RelativeLayout
import cn.kpa.viewpagerfragmentupdate.adapter.HomePageAdapter
import cn.kpa.viewpagerfragmentupdate.fragment.Blank1Fragment
import cn.kpa.viewpagerfragmentupdate.fragment.Blank2Fragment
import cn.kpa.viewpagerfragmentupdate.fragment.Blank3Fragment

@Suppress("UNUSED_EXPRESSION")
/**
 * Created by mr.kong on 2018/3/6.
 *
 */
class PageView : RelativeLayout {
    private var mList: MutableList<String> = arrayListOf()
    private lateinit var mActivity: AppCompatActivity
    private lateinit var adapter: HomePageAdapter
    private lateinit var mViewPager: ViewPager
    val vList: ArrayList<Fragment> = ArrayList()
    private lateinit var fragment :Blank2Fragment
    constructor(vActivity: AppCompatActivity, mList: MutableList<String>) : super(vActivity) {
        this.mList = mList
        mActivity = vActivity
        val inflater: LayoutInflater = vActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.home_page_main, this)
        mViewPager = view.findViewById(R.id.mViewPage_page)
        addFragment()
    }

    fun update() {
        val mListUpdate = ArrayList<String>()
        for (i in 0 until 100) {
            mListUpdate.add("更新的数据$i")
        }
        fragment.update(mListUpdate)
        adapter.setFragments(vList)
    }

    private fun addFragment() {
        vList.add(Blank1Fragment.newInstance())
        fragment = Blank2Fragment.newInstance(mList)
        vList.add(fragment)
        vList.add(Blank3Fragment.newInstance())
        adapter = HomePageAdapter(mActivity, mActivity.supportFragmentManager, vList)
        mViewPager.adapter = adapter
        mViewPager.currentItem = 1
    }

}



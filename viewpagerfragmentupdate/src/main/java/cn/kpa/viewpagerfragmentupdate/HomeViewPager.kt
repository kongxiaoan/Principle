package cn.kpa.viewpagerfragmentupdate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RelativeLayout

/**
 * Created by mr.kong on 2018/3/6.
 */
class HomeViewPager(private var mList: ArrayList<String>, private var mContext: Context) :
        RelativeLayout(mContext) {

    private lateinit var mListView: ListView
    private var vList: ArrayList<String> = arrayListOf()
    private lateinit var adapter: ArrayAdapter<String>

    init {
        val inflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.home_page_view, this)
        vList.addAll(mList)
        initView(view)
    }

    fun upDate(list: ArrayList<String>) {
        vList.clear()
        vList.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun initView(view: View) {
        mListView = view.findViewById(R.id.mList_home_page)
        adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, vList)
        mListView.adapter = adapter
    }

}



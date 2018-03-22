package cn.kpa.viewpagerfragmentupdate.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import cn.kpa.viewpagerfragmentupdate.HomeViewPager

import cn.kpa.viewpagerfragmentupdate.R
import cn.kpa.viewpagerfragmentupdate.adapter.HomePageAdapter


@Suppress("UNUSED_EXPRESSION")
/**
 * A simple [Fragment] subclass.
 * Use the [Blank2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Blank2Fragment : Fragment(), HomePageAdapter.TitleInterface {
    private lateinit var mView: RelativeLayout
    private lateinit var mHomeViewPager: HomeViewPager
    override fun getPageTitle(context: Context): String {
        return "page 2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.e("onCreateView", "onCreateView")
        mHomeViewPager = HomeViewPager(mList, activity)
//        update()
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        mView = view.findViewById(R.id.main_viewpage)
        mHomeViewPager.upDate(mList)
        mView.addView(mHomeViewPager)
    }

    fun update(list: ArrayList<String>){
        mList.clear()
        mList.addAll(list)
    }

    companion object {
        var mList: ArrayList<String> = arrayListOf()
        // TODO: Rename and change types and number of parameters
        fun newInstance(mutableList: MutableList<String>): Blank2Fragment {
            val fragment = Blank2Fragment()
            mList.clear()
            mList.addAll(mutableList as ArrayList<String>)
            return fragment
        }
    }
}

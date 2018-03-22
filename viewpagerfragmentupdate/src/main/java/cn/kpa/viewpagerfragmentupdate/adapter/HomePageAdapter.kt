package cn.kpa.viewpagerfragmentupdate.adapter

import android.content.Context
import android.support.v4.app.*
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.ViewGroup
import android.view.WindowId
import cn.kpa.viewpagerfragmentupdate.fragment.Blank2Fragment

/**
 * Created by mr.kong on 2018/3/6.
 */
class HomePageAdapter(val context: Context, fm: FragmentManager, private var mList: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {


    private var mFragmentManager: FragmentManager = fm

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return (mList[position] as TitleInterface).getPageTitle(context)
    }

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    interface TitleInterface {
        fun getPageTitle(context: Context): String
    }

    public fun setFragments(fragments:ArrayList<Fragment>){
        if(mList != null){
            var transaction = mFragmentManager.beginTransaction()
            for (f in mList) {
                transaction.remove(f)
            }
            transaction.commit()
            transaction = null
            mFragmentManager.executePendingTransactions()
        }
        mList = fragments
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

}
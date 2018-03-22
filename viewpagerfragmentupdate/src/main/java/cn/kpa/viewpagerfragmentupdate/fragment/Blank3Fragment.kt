package cn.kpa.viewpagerfragmentupdate.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.kpa.viewpagerfragmentupdate.R
import cn.kpa.viewpagerfragmentupdate.adapter.HomePageAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [Blank3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Blank3Fragment : Fragment(), HomePageAdapter.TitleInterface {
    override fun getPageTitle(context: Context): String {
        return "page 3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        fun newInstance(): Blank3Fragment {
            val fragment = Blank3Fragment()

            return fragment
        }
    }

}// Required empty public constructor

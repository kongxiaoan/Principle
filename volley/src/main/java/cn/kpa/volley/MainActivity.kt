package cn.kpa.volley

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mQueue: RequestQueue? = null

    override fun onClick(v: View) {
        when (v.id) {
            R.id.stringRequest -> {
                stringRequests()
            }
        }
    }

    private fun stringRequests() {
        StringRequest("", Response.Listener<String> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }, Response.ErrorListener {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mQueue = VolleyApplication().getQueue()
        initClick()
    }

    private fun initClick() {
        stringRequest.setOnClickListener(this)
    }
}

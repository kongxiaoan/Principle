package cn.kpa.volley

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


/**
 * Created by mr.kong on 2018/2/8.
 *
 */
class VolleyApplication : Application() {
    private var mQueue: RequestQueue? = null
    override fun onCreate() {
        super.onCreate()
        mQueue = Volley.newRequestQueue(applicationContext)
    }

    /**
     * 获取请求队列
     */
    fun getQueue(): RequestQueue? {
        return if (mQueue != null) {
            mQueue
        } else {
            null
        }
    }

}
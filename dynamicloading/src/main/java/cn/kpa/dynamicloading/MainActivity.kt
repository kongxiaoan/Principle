package cn.kpa.dynamicloading

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var classLoader:ClassLoader = classLoader
        if (classLoader != null) {
            Log.e(TAG, "第一个打印" + classLoader.toString())
            while (classLoader.parent != null) {
                classLoader = classLoader.parent
                Log.e(TAG,"第二个打印" + classLoader.toString())
            }
        }
    }
}

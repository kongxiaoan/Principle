package com.android.bessel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupWindow
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_1.setOnClickListener({
            //            CustomPopWindow.getInstance(this)
//                    .showPopWindow() object : CustomPopWindow.OnClickListener {
//                override fun doOk() {
//                    Toast.makeText(this@MainActivity, "start", Toast.LENGTH_LONG).show()
//                }
//
//                override fun cancel() {
//                    Toast.makeText(this@MainActivity, "取消", Toast.LENGTH_LONG).show()
//                }
//
//                override fun close() {
//                    Toast.makeText(this@MainActivity, "关闭", Toast.LENGTH_LONG).show()
//                }
//
//                override fun dismiss(popWindow: PopupWindow?) {
//                    if (popWindow != null) {
//
//                        popWindow?.dismiss()
//                    }
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//            }).showPopWindow()

            CustomPopWindow.Builder(this)
                    .show()
                    .setOnClickListener(object : CustomPopWindow.OnClickListener {
                        override fun doOk() {
                            Toast.makeText(this@MainActivity, "start", Toast.LENGTH_LONG).show()
                        }

                        override fun cancel() {
                            Toast.makeText(this@MainActivity, "start", Toast.LENGTH_LONG).show()
                        }

                        override fun close() {
                            Toast.makeText(this@MainActivity, "start", Toast.LENGTH_LONG).show()
                        }

                    }).build()
        })
    }
}

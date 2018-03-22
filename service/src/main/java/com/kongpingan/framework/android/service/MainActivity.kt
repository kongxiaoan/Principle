package com.kongpingan.framework.android.service

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        public val UPLOAD_RESULT = "com.blogcodes.intentservice.UPLOAD_RESULT"

    }
}

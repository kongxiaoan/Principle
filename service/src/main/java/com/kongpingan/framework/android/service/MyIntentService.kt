package com.kongpingan.framework.android.service

import android.app.IntentService
import android.content.Intent
import android.content.Context

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService {
    constructor() : super("myintentService")

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (ACTION_UPLOAD_IMG.equals(action)) {
                val path: String = intent.getStringExtra(ExTRA_IMG_PATH)
                handleUploadImg(path)
            }
        }
    }

    private fun handleUploadImg(path: String) {
        //模拟上传时间
        Thread.sleep(3000)
        val intent = Intent(MainActivity.UPLOAD_RESULT)
        intent.putExtra(ExTRA_IMG_PATH, path)
        sendBroadcast(intent)
    }

    companion object {
        private val ACTION_UPLOAD_IMG = "com.android.intentservice.upload.img"
        private val ExTRA_IMG_PATH = "com.android.intentservice.upload.path"

        fun startMyIntentService(context: Context, path: String) {
            var intent = Intent(context, MyIntentService::class.java)
            intent.action = ACTION_UPLOAD_IMG
            intent.putExtra(ExTRA_IMG_PATH, path)
            context.startService(intent)
        }

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

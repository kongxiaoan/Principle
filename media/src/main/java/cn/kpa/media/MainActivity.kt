package cn.kpa.media

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var mExecutorService: ExecutorService
    private var mMediaRecorder: MediaRecorder? = null
    private var mRecorderFile: File? = null
    private var startRecorderTime = 0L
    private var stopRecorderTime = 0L
    private var mHandler = Handler(Looper.getMainLooper())
    private var mIsPlaying = false
    private var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mExecutorService = Executors.newSingleThreadExecutor()

        /**
         * 时下触摸录音 松开的时候录音结束
         */
        mMediacorder.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startRecorder()
                }
                MotionEvent.ACTION_CANCEL -> {

                }
                MotionEvent.ACTION_UP -> {
                    stopRecorder()
                }

            }
            true
        }
        mPlaying.setOnClickListener {
            playRecorder()
        }

    }

    /**
     * 停止录音
     */
    private fun stopRecorder() {
        mMediacorder.text = "录音结束"
        mExecutorService.submit(object : Runnable {
            override fun run() {
                if (!doStop()) {
                    recorderFial()
                }
                releaseRecorder()
            }

        })

    }

    /**
     * 关闭录音
     */
    @SuppressLint("SetTextI18n")
    private fun doStop(): Boolean {
        try {
            mMediaRecorder?.stop()
            stopRecorderTime = System.currentTimeMillis()
            val second = (stopRecorderTime - startRecorderTime).toInt() / 1000
            if (second < 3) return false
            mHandler.post {
                mMediacorder.text = "录知成功" + second + "秒"
            }
        } catch (e: Exception) {

        }
        return true
    }

    /**
     * 释放录音
     */
    private fun releaseRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder?.release()
            mMediaRecorder = null
        }
    }

    /**
     * 启动录音
     */
    private fun doStart(): Boolean {
        try {
            //创建MediaRecorder
            mMediaRecorder = MediaRecorder()
            //创建录音文件
            mRecorderFile = File(Environment.getExternalStorageDirectory().absolutePath +
                    "/recorderdemo/" + System.currentTimeMillis() + ".m4a")
            //配置MediaRecorder
            //设置从麦克风采集
            mMediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            //设置保存格式为mp4
            mMediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            //设置Android适中的采样率
            mMediaRecorder?.setAudioSamplingRate(44100)
            //通用的AAC 编码格式
            mMediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            //设置音质频率
            mMediaRecorder?.setAudioEncodingBitRate(96000)
            //设置文件保存的路径
            mMediaRecorder?.setOutputFile(mRecorderFile?.absolutePath)

            mMediaRecorder?.prepare()
            mMediaRecorder?.start()

            startRecorderTime = System.currentTimeMillis()
        } catch (e: Exception) {
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show()
            return false
        }
        //记录开始录音的时间 用于统计时长 小于3秒 不能发送
        return true
    }

    private fun recorderFial() {
        mRecorderFile = null
        mHandler.post {
            mMediacorder.text = "录音失败"
        }

    }

    /**
     * 开始录音
     */
    private fun startRecorder() {

        mMediacorder.text = "正在录音"
        mExecutorService.submit(object : Runnable {
            override fun run() {
                //释放上一次录音
                releaseRecorder()
                //开始录音
                if (!doStart()) {
                    recorderFial()
                }
            }

        })
    }

    /**
     * 播放录音
     */
    private fun playRecorder() {
        if (!mIsPlaying) {
            mExecutorService.submit({
                doPlay(mRecorderFile!!)
            })
        } else {
            Toast.makeText(this, "正在播放", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doPlay(mRecorderFile: File) {
        try {
            mMediaPlayer = MediaPlayer()
            //设置声音文件
            mMediaPlayer?.setDataSource(mRecorderFile.absolutePath)
            //配置音量 中等音量
            mMediaPlayer?.setVolume(1.toFloat(), 1.toFloat())
            //是否循环播放
            mMediaPlayer?.isLooping = false
            mMediaPlayer?.setOnCompletionListener { mp ->
                stopPlayer()
            }
            mMediaPlayer?.setOnErrorListener(object : MediaPlayer.OnErrorListener {
                override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                    stopPlayer()
                    Toast.makeText(this@MainActivity, "播放失败", Toast.LENGTH_SHORT).show()
                    return true
                }

            })

            mMediaPlayer?.prepare()
            mMediaPlayer?.start()
        } catch (e: Exception) {
            stopPlayer()
        }

    }

    private fun stopPlayer() {
        mIsPlaying = false
        mMediaPlayer?.release()
        mMediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        //停止线程
        mExecutorService.shutdownNow()
        releaseRecorder()
    }

}

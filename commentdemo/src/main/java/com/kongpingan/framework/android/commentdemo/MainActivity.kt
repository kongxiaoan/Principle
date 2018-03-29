package com.kongpingan.framework.android.commentdemo

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kongpingan.framework.android.commentdemo.adapter.CommentAdapter
import com.kongpingan.framework.android.commentdemo.bean.Comment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("NewApi")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.comment -> {
                val inputMethodManager:InputMethodManager = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
                rl_enroll.visibility = View.GONE
                rl_comment.visibility = View.VISIBLE
            }
            R.id.hide_down -> {
                rl_enroll.visibility = View.VISIBLE
                rl_comment.visibility = View.GONE
                val inputMethodManager = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(comment_content.windowToken, 0)
            }

            R.id.comment_send -> {
                sendComment()
            }
        }
    }

    private fun sendComment() {
        if (comment_content.text.toString().equals("")) {
            Toast.makeText(this, "评论不能为空", Toast.LENGTH_SHORT).show()
        } else {
            val comment = Comment()
            comment.name = "我是评论者" + mList.size + 1
            comment.content = comment_content.text.toString()
            mAdapter.addComment(comment)
            comment_content.setText("")

        }
    }

    private val mList: ArrayList<Comment> = ArrayList()
    private lateinit var mAdapter: CommentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = CommentAdapter(this, mList)
        comment_list.adapter = mAdapter
        setListener()
    }

    private fun setListener() {
        comment.setOnClickListener(this)
        hide_down.setOnClickListener(this)
        comment_send.setOnClickListener(this)
    }


}

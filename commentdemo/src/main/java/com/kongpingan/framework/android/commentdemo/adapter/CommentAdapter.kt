package com.kongpingan.framework.android.commentdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kongpingan.framework.android.commentdemo.R
import com.kongpingan.framework.android.commentdemo.bean.Comment
import java.util.*


/**
 * Created by mr.kong on 2018/3/29.
 */
class CommentAdapter : BaseAdapter {
    private var context: Context
    private var mList: ArrayList<Comment>
    private var mInflater: LayoutInflater

    constructor(context: Context, mList: MutableList<Comment>) : super() {
        this.context = context
        this.mList = mList as ArrayList<Comment>
        mInflater = LayoutInflater.from(context)
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        val holder: ViewHolder
        var view: View
        if (convertView == null) {
            holder = ViewHolder()
            view = mInflater.inflate(R.layout.items_comment, viewGroup, false)
            holder.comment_name = view.findViewById<View>(R.id.comment_name) as TextView
            holder.comment_content = view.findViewById<View>(R.id.comment_content) as TextView

            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.comment_name.text = mList.get(i).name
        holder.comment_content.text = mList.get(i).content

        return view
    }


    override fun getItem(position: Int): Any {
        return mList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mList.size
    }


    class ViewHolder {
        internal lateinit var comment_name: TextView
        internal lateinit var comment_content: TextView
    }

    fun addComment(comment: Comment) {
        mList.add(comment)
        notifyDataSetChanged()
    }
}
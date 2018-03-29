package com.kongpingan.framework.android.commentdemo.bean

/**
 * Created by mr.kong on 2018/3/29.
 */
class Comment {
    var name = ""
    var content = ""

    constructor(name: String, content: String) {
        this.name = name
        this.content = content
    }

    constructor() {

    }
}
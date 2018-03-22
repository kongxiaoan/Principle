package com.android.bessel

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by mr.kong on 2017/11/20.
 */
class Bessel : View {
    private var centerX: Int = 100
    private var centerY: Int = 100
    private lateinit var path: Path
    private lateinit var paint: Paint

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.strokeWidth = 5F
        paint.style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        centerX = MeasureSpec.getSize(widthMeasureSpec) / 2
        centerY = MeasureSpec.getSize(heightMeasureSpec) / 2

        Log.e("View    ", "" + centerX + "      " + centerY)
        Log.e("View    ", "" + centerX + "      " + centerY)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCoordinate(canvas)
    }

    private fun drawCoordinate(canvas: Canvas?) {
        if (canvas != null) {
            canvas.save()
            paint.strokeWidth = 8F
            paint.color = Color.CYAN
            //绘制x轴
            canvas.drawLine(0F, centerY.toFloat(), centerY * 2.toFloat(), centerY.toFloat(), paint)
            canvas.drawLine(centerX.toFloat(), 0F, centerX.toFloat(), centerY * 2.toFloat(), paint)
            canvas.restore()
        }
    }
}
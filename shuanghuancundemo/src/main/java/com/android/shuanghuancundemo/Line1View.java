package com.android.shuanghuancundemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mr.kong on 2017/11/16.
 * 绘制曲线
 */

public class Line1View extends View {

    private Paint paint;
    //上一个点的坐标
    private int preX, preY;
    //当前点的坐标
    private int currentX, currentY;
    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;

    public Line1View(Context context) {
        this(context, null);
    }

    public Line1View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Line1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }

    /**
     * 组件的大小发生变化的时候 调用的方法 在这里创建Bitmap
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (bitmapBuffer == null) {
            //创建Bitmap对象 首先将会在这个对象上面进行绘制图 因为不知道将要创建
            //多大的视图 所以在onSizeChanged 方法中 创建
            bitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.
                    Config.ARGB_8888);
            //创建Bitmap的对象
            bitmapCanvas = new Canvas(bitmapBuffer);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将Bitmap 绘制在View的Canvas上
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
    }

    /**
     * 处理手势
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = x;
                preY = y;
                //当手指按下的时候记录按下的点的坐标
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动的时候 记录当前点的坐标
                currentX = x;
                currentY = y;
                //将当前的点和上一个点连接在一起
                bitmapCanvas.drawLine(preX, preY, currentX, currentY, paint);
                this.invalidate();
                //当前点成为上一个点
                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();//抬起的收最后一次重绘（会调用onDraw）
                break;
        }
        return true;
    }
}

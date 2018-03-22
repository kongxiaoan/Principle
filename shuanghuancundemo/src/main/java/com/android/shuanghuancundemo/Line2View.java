package com.android.shuanghuancundemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mr.kong on 2017/11/16.
 * 绘制曲线
 * 和Line1View 相比较而言
 * <p>
 * Path 可以用于保存实时绘图坐标 避免调用invalidate（）方法重会时因ViewRoot的
 * schedueTraversals()方法发送异步请求出现问题
 * Path 可以绘制复杂图形
 * 绘图效率高
 */

public class Line2View extends View {

    private Paint paint;
    //上一个点的坐标
    private int preX, preY;
    //当前点的坐标
    private int currentX, currentY;
    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;
    private Path path;

    public Line2View(Context context) {
        this(context, null);
    }

    public Line2View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Line2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(50);
        path = new Path();
    }

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
        canvas.drawPath(path, paint);
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
                path.reset();
                preX = x;
                preY = y;
                path.moveTo(x, y);
                //当手指按下的时候记录按下的点的坐标
                break;
            case MotionEvent.ACTION_MOVE:
//                path.quadTo(preX, preY, x, y);
//                invalidate();
//                //当前点成为上一个点
//                preX = x;
//                preY = y;
                //手指移动的过程中只显示绘制的过程
                //使用贝塞尔曲线进行绘图 需要一个起点（preX,preY）
                //一个终点(x,y) 一个控制点((preX+x)/2,(preY+y)/2)
                int controlX = (x + preX) / 2;
                int controlY = (y + preY) / 2;
                path.quadTo(controlX, controlY, x, y);
                invalidate();
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:

                bitmapCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }
}

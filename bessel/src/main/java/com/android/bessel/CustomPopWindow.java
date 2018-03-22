package com.android.bessel;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by mr.kong on 2017/11/20.
 */

public class CustomPopWindow implements View.OnClickListener {

    private Context context;
    private LayoutInflater inflater;
    private PopupWindow popupWindow;
    private LinearLayout start;
    private LinearLayout close;
    private LinearLayout cancel;
    private RelativeLayout outsideLayout;
    private OnClickListener mListener;

    private CustomPopWindow(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private CustomPopWindow(CustomPopWindow popWindow) {
        this.context = popWindow.context;
        this.mListener = popWindow.mListener;
    }

    public static class Builder {
        private CustomPopWindow popWindow;

        public Builder(Context context) {
            popWindow = new CustomPopWindow(context);
        }


        public Builder setOnClickListener(OnClickListener listener) {
            popWindow.mListener = listener;
            return this;
        }

        public Builder show() {
            popWindow.showPopWindow();
            return this;
        }

        public CustomPopWindow build() {
            return new CustomPopWindow(popWindow);
        }

    }


    public interface OnClickListener {
        void doOk();

        void cancel();

        void close();

    }

    public void showPopWindow() {
        View view = inflater.inflate(R.layout.popwindow, null);
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                , true);
        init(view);
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    private void init(View view) {
        start = ((LinearLayout) view.findViewById(R.id.start));
        close = ((LinearLayout) view.findViewById(R.id.close));
        cancel = ((LinearLayout) view.findViewById(R.id.cancel));
        outsideLayout = ((RelativeLayout) view.findViewById(R.id.layout));
        initOnClick();
    }

    private void initOnClick() {
        start.setOnClickListener(this);
        close.setOnClickListener(this);
        cancel.setOnClickListener(this);
        outsideLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mListener.doOk();
                break;
            case R.id.close:
                mListener.close();
                break;
            case R.id.cancel:
                mListener.cancel();
                break;
            case R.id.layout:
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                break;
        }
    }
}

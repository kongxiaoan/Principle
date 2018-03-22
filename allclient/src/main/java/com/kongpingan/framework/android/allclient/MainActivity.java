package com.kongpingan.framework.android.allclient;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MSG_SUM = 0x110;
    private boolean isConnection = false;
    private TextView mTvState;
    private Button mBtnAdd;
    private LinearLayout mLayoutContainer;
    private int mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始绑定服务
        bindServiceInvoked();

        mTvState = (TextView) findViewById(R.id.id_tv_callback);
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mLayoutContainer = (LinearLayout) findViewById(R.id.id_ll_container);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = mA++;
                int b = (int) (Math.random() * 100);

                TextView textView = new TextView(MainActivity.this);
                textView.setText(a + " + " + b + " = caculating ..");
                textView.setId(a);
                mLayoutContainer.addView(textView);

                Message msgeFromClient = Message.obtain(null, MSG_SUM, a, b);
                msgeFromClient.replyTo = mMessenger;//Message.replyTo指向的是一个mMessenger，
                if (isConnection) {
                    try {
                        //给服务端发送消息
                        mServiceMessenger.send(msgeFromClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUM:
                    TextView viewById = (TextView) mLayoutContainer.findViewById(msg.arg1);
                    viewById.setText(viewById.getText() + "=>" + msg.arg2);
                    break;
            }
            super.handleMessage(msg);
        }
    });

    private void bindServiceInvoked() {
        Intent intent = new Intent();
        intent.setAction("com.kpa.aidl.message");
        Intent displayIntent = new Intent(implicitCallConversionSisplayCall(this, intent));
        bindService(displayIntent, connection, Context.BIND_AUTO_CREATE);

    }

    /**
     * 将隐式Intent转换成显示INtent
     *
     * @param context
     * @param implicitIntent
     * @return
     */
    private Intent implicitCallConversionSisplayCall(Context context, Intent implicitIntent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentServices(implicitIntent, 0);
        if (resolveInfos.size() != 1 && resolveInfos == null) {
            return null;
        }
        ResolveInfo resolveInfo = resolveInfos.get(0);
        String packageName = resolveInfo.serviceInfo.packageName;
        String className = resolveInfo.serviceInfo.name;
        ComponentName componentName = new ComponentName(packageName, className);

        Intent intent = new Intent(implicitIntent);
        intent.setComponent(componentName);
        return intent;

    }

    private Messenger mServiceMessenger;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServiceMessenger = new Messenger(service);
            isConnection = true;
            mTvState.setText("连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceMessenger = null;
            isConnection = false;
            mTvState.setText("断开连接");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}

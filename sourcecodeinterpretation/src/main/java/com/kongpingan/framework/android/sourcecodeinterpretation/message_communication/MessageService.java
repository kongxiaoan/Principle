package com.kongpingan.framework.android.sourcecodeinterpretation.message_communication;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by mr.kong on 2018/3/22.
 */

public class MessageService extends Service {
    private static final int MSG_SUM = 0x110;
    //最好换成HandlerThread的形式
    @SuppressLint("HandlerLeak")
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgfromClient) {
            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
            switch (msgfromClient.what) {
                //msg 客户端传来的消息
                case MSG_SUM:
                    msgToClient.what = MSG_SUM;
                    try {
                        //模拟耗时
                        Thread.sleep(2000);
                        msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                        msgfromClient.replyTo.send(msgToClient);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            super.handleMessage(msgfromClient);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}

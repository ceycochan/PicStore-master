package com.nshane.picstore.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.nshane.picstore.R;
import com.nshane.picstore.ui.ServiceActivity;

/**
 * Created by lbl on 2017-6-19.
 */

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    int progress = 66;

    public class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d("cg", "开始下载");
        }

        public int getProgress() {
            Log.d("cg", "获取进度" + progress);
            return progress;
        }
    }


    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 服务中常用方法
     * onCreate / onStartCommand / onDestroy
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //此处创建foreground service
        Intent intent = new Intent(this, ServiceActivity.class); // 前台服务依附于当前Activity
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is title")
                .setContentText("This are content")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)  // 服务的左边icon
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fab_add)) //
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);

        /**
         * Service中要执行的业务逻辑放在 onStartCommand下
         * remark: 每次启动Service都会被调用,onCreate只在服务第一次创建的时候调用
         *
         * @param intent
         * @param flags
         * @param startId
         * @return
         */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * ★★★
     * remark:
     * 当一个Service的startService(); & BindService() 同时被调用后,
     * 须同时调用stopService() & unBindService(), 继而onDestroy()才会执行。
     */
}

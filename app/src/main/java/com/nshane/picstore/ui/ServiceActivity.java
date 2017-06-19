package com.nshane.picstore.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nshane.picstore.R;
import com.nshane.picstore.services.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lbl on 2017-6-19.
 */

public class ServiceActivity extends AppCompatActivity {
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service; // 向下转型
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
//                Intent intentStart = new Intent(this, MyService.class); //
//                startService(intentStart);
                Intent intentBind = new Intent(this, MyService.class);
                bindService(intentBind, connection, BIND_AUTO_CREATE); //activity & service 绑定
                break;
            case R.id.btn_2:
//                Intent intentStop = new Intent(this, MyService.class);
//                stopService(intentStop);
                unbindService(connection);
                break;
            case R.id.btn_3:
                startActivity(new Intent(this, DownLoadActivity.class));
                break;
            default:
                break;
        }
    }


}

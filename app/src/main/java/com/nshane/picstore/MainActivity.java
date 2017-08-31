package com.nshane.picstore;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nshane.picstore.config.Constant;
import com.nshane.picstore.http.MyTask;
import com.nshane.picstore.ui.FeedbackActivity;
import com.nshane.picstore.ui.FlashAnimActivity;
import com.nshane.picstore.ui.ImmersiveActivity;
import com.nshane.picstore.ui.PopWindowActivity;
import com.nshane.picstore.ui.ServiceActivity;
import com.nshane.picstore.ui.ToolbarActivity;
import com.nshane.picstore.utils.FileUtil;
import com.nshane.picstore.utils.ProgressDialog;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_6)
    Button btn6;
    @BindView(R.id.btn_7)
    Button btn7;
    @BindView(R.id.btn_8)
    Button btn8;
    @BindView(R.id.btn_10)
    Button btn10;
    @BindView(R.id.btn_11)
    Button btn11;
    @BindView(R.id.btn_13)
    Button btn13;
    @BindView(R.id.btn_14)
    Button btn14;
    @BindView(R.id.btn_15)
    Button btn15;
    private TimerTask timerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_6, R.id.btn_7, R.id.btn_8,
            R.id.btn_10, R.id.btn_11, R.id.btn_13,
            R.id.btn_14, R.id.btn_15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                /**
                 * remark:
                 *
                 * if specified activity invoked is not MainActivity (!!!) of the App, the activity intent to be
                 * invoked should be add  android:exported="true"  which alleged @ manifest
                 */

                // 启动设备下某一指定APP
                intent.setComponent(new ComponentName("com.nshane.databinding", "com.nshane.databinding.ui.MainActivity"
                ));

                startActivity(intent);
                break;
            case R.id.btn_2:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.btn_6:
                startActivity(new Intent(this, DataFormatActivity.class));
                break;
            case R.id.btn_7:
                // simulating share img & txt
                shareApp();
                break;
            case R.id.btn_8:
                //simulating feedback
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.btn_10:
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case R.id.btn_11:
                startActivity(new Intent(this, ImmersiveActivity.class));
                break;
            case R.id.btn_13:
                startActivity(new Intent(this, PopWindowActivity.class));
                break;
            case R.id.btn_14:
                startActivity(new Intent(this, FlashAnimActivity.class));
                break;
            case R.id.btn_15:
               // Dialog invoking @ around the app
                ProgressDialog.getInstance().showProgress(this, "Loading");

                Timer timer = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);

                    }
                };
                timer.schedule(timerTask, 2500);
                break;
            default:
                break;
        }
    }

    private void shareApp() {   //设备平台的分享

        /**
         * if here used at fragment,declare activity judgement
         * such as
         * final Activity activity = getActivity();
         if (activity == null) {
         return;
         }
         */
        final String picFileName = "share_funny.png";
        final String shareTo = getString(R.string.share);
        File dirFile = new File(Constant.IMAGE_PATH);
        if (!dirFile.exists()) { // if direFile is not exists condition
            dirFile.mkdir();  //Creates the directory named by this abstract pathname.
        }

        final String sharePicPath = Constant.IMAGE_PATH + picFileName;
        final String description = "This is a funny pic";
        //multi-thread opt
        Runnable runSend = new Runnable() {
            @Override
            public void run() {
                File f = FileUtil.write2SDFromInput(Constant.IMAGE_PATH, picFileName,
                        FileUtil.getInputStreamFromAsset(getApplicationContext(), "material/pics/app_funny.png"));
                if (f != null && f.exists()) {
                    //由文件获取uri
                    //考虑文件是否存在
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Uri imageUri = Uri.fromFile(new File(sharePicPath));
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri); // the effect of this method：
                            shareIntent.putExtra(Intent.EXTRA_TEXT, description);
                            shareIntent.setType("image/*");
                            startActivity(Intent.createChooser(shareIntent, shareTo));
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, description);  // 如果无法找到图片,则已文本方式输出
                            intent.setType("text/plain");
                            //设置分享列表的标题,并且每次都显示分享列表
                            startActivity(Intent.createChooser(intent, shareTo));
                        }
                    });

                }
            }
        };

        if (FileUtil.isFileExist(sharePicPath)) {
            if (new File(sharePicPath).length() < 100) {
                new File(sharePicPath).delete();
                MyTask.runInBackground(runSend, true);
                return;
            }
            Uri imageUri = Uri.fromFile(new File(sharePicPath));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, description);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, shareTo));
            return;
        }
        MyTask.runInBackground(runSend, false);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Toast.makeText(MainActivity.this, "下载完毕", Toast.LENGTH_SHORT).show();
                ProgressDialog.getInstance().cancelProgress();
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerTask.cancel();
    }
}

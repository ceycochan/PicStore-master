package com.nshane.picstore;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.nshane.picstore.utils.LogUtil;
import com.nshane.picstore.utils.SharePreferenceManager;

import java.util.Random;

/**
 * Created by lbl on 2017-9-2.
 */

public class PicStoreApplication extends Application {
    private static PicStoreApplication mInstance;

    public static Context context;


    public static PicStoreApplication getInstance() {
        return mInstance;
    }

    public PicStoreApplication() {
        mInstance = this;
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LogUtil.d("cg", "picStore启动了");

        Random r = new Random();
        int i = r.nextInt(10);
        LogUtil.d("cg", "随机到的数字" + i);
        SharePreferenceManager.setInt(mInstance, SharePreferenceManager.BGInfoXml.XML_NAME,
                SharePreferenceManager.BGInfoXml.BACKGROUND.key, i);

    }

}

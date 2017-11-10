package com.nshane.picstore.ui;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.nshane.picstore.R;

/**
 * Created by lbl on 2017-6-27.
 * <p>
 * http://blog.csdn.net/guolin_blog/article/details/51763825
 */

public class ImmersiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
        // 不要使隐藏标题栏代码,直接在style选择无no.action.bar 主题
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);  // hiding  toolbar

        highApiEffect();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffect() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏 @底部 这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }


    private void setImmersiveLayout() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //使statusBar透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //适配 华为 的沉浸式页面
    private void setImOnSpeDevice() {
    }

}

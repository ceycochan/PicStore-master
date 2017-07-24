package com.nshane.picstore.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nshane.picstore.R;

/**
 * Created by lbl on 2017-6-27.
 *
 * http://blog.csdn.net/guolin_blog/article/details/51763825
 *
 */

public class ImmersiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);

        setFakeImmersive();

        setAuthenticImmersive();

    }

    private void setAuthenticImmersive() {

    }

    private void setFakeImmersive() {
        //        if (Build.VERSION.SDK_INT >= 21) {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
    }


}

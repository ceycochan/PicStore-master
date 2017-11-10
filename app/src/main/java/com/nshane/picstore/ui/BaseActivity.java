package com.nshane.picstore.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nshane.picstore.R;

/**
 * Created by lbl on 2017-9-2.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void setToolbar(@Nullable View view, @Nullable String title, @Nullable Drawable icon) {
        Toolbar mTb = (Toolbar) view.findViewById(R.id.tb_toolbar);
        mTb.setTitle(title);
        mTb.setTitleTextColor(getResources().getColor(R.color.colorToolBarBText));
        mTb.setNavigationIcon(icon);
        setSupportActionBar(mTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}

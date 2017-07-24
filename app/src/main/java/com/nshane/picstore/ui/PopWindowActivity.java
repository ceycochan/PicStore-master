package com.nshane.picstore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nshane.picstore.R;
import com.nshane.picstore.views.MyPopWindow;

/**
 * Created by lbl on 2017-7-12.
 */

public class PopWindowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_showPop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        btn_showPop = (Button) findViewById(R.id.btn_show_pop);
        btn_showPop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_show_pop:
//                Toast.makeText(this, "展示", Toast.LENGTH_SHORT).show();
                MyPopWindow myPopWindow = new MyPopWindow(this);
                myPopWindow.showAtDropDownLeft(v);
                break;
        }
    }




}

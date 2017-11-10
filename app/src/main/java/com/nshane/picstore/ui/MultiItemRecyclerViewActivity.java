package com.nshane.picstore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nshane.picstore.R;

/**
 * Created by lbl on 2017-9-2.
 */

public class MultiItemRecyclerViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_rv);
        setToolbar(getWindow().getDecorView(), "上天的RecyclerView", getResources().getDrawable(R.drawable.btn_tb_back));
    }


}

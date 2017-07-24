package com.nshane.picstore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import com.nshane.picstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lbl on 2017-6-27.
 */

public class ToolbarActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.btn_tb_back));
        setSupportActionBar(mToolbar);
        /* 这些通过ActionBar来设置也是一样的，注意要在setSupportActionBar(toolbar);
        之后，不然就报错了 */
        // getSupportActionBar().setTitle("标题");
        // getSupportActionBar().setSubtitle("副标题");
        // getSupportActionBar().setLogo(R.drawable.ic_launcher);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

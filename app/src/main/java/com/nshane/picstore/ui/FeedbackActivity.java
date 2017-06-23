package com.nshane.picstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

import com.nshane.picstore.R;

/**
 * Created by lbl on 2017-6-22.
 */

public class FeedbackActivity extends AppCompatActivity {
    AutoCompleteTextView mAutoCompleteTextView;
    TextInputLayout mTitleTextInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
//        initActionBar(getString(R.string.feedback));
//        setToolbarSub(getString(R.string.feedback));
        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.feedback_content);
        mTitleTextInput = (TextInputLayout) findViewById(R.id.layout_text_input);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_feedback:
                sendContent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //getString(R.string.feedback)
    private void sendContent() {
        String content = mAutoCompleteTextView.getText().toString();
        if (content.length() < 6) {
            mTitleTextInput.setErrorEnabled(true);
            mTitleTextInput.setError("title length must >= 6"); // 错误提示
            return;
        } else {
            mTitleTextInput.setErrorEnabled(false);
        }
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"1742646436@qq.com"});
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "返回");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        String title = "send email"; // 分享选择栏的title
        Intent chooserIntent = Intent.createChooser(intent, title);
        startActivity(chooserIntent);  //this intent starts a chooserActivity to pop a dialog listing available email app. you can choose any one app to send email(like gmail , email...)
    }
}

package com.nshane.picstore;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nshane.picstore.ui.ServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;
    @BindView(R.id.btn_6)
    Button btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }





    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                /**
                 * if specified activity invoked is not MainActivity of the App, the activity intent to be
                 * invoked should be add  android:exported="true"
                 */
                intent.setComponent(new ComponentName("com.nshane.databinding", "com.nshane.databinding.ui.MainActivity"
                ));
                startActivity(intent);
                break;
            case R.id.btn_2:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                startActivity(new Intent(this, DataFormatActivity.class));
                break;
        }
    }
}

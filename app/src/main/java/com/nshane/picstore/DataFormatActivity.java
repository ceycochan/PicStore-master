package com.nshane.picstore;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lbl on 2017-6-19.
 */

public class DataFormatActivity extends AppCompatActivity {

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
    private TextView tv_date;
    private SimpleDateFormat myFmt;  // should be inherit java.text
    private Date now;
    private SimpleDateFormat myFmt1;
    private SimpleDateFormat myFmt2;
    private SimpleDateFormat myFmt3;
    private SimpleDateFormat myFmt4;

    /**
     * SimpleDateFormat函数语法:
     * <p>
     * G 年代标志符
     * y 年
     * M 月
     * d 日
     * h 时 在上午或下午 (1~12)
     * H 时 在一天中 (0~23)
     * m 分
     * s 秒
     * S 毫秒
     * E 星期
     * D 一年中的第几天
     * F 一月中第几个星期几
     * w 一年中第几个星期
     * W 一月中第几个星期
     * a 上午 / 下午 标记符
     * k 时 在一天中 (1~24)
     * K 时 在上午或下午 (0~11)
     * z 时区
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataformat);
        ButterKnife.bind(this);
        tv_date = (TextView) findViewById(R.id.tv_date);
        formatStore();
    }

    private void formatStore() {
        now = new Date();
//        myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        myFmt = new SimpleDateFormat("EEE MMM d HH:mm");
        myFmt1 = new SimpleDateFormat("yy/MM/dd HH:mm");
        //等价于now.toLocaleString()
        myFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myFmt3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        myFmt4 = new SimpleDateFormat(
                "一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区");
    }


    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Toast.makeText(this, "Show Time", Toast.LENGTH_SHORT).show();
                String date = myFmt.format(now);
                tv_date.setText(date);
                break;
            case R.id.btn_2:
                tv_date.setText(myFmt1.format(now));
                break;
            case R.id.btn_3:
                tv_date.setText(myFmt2.format(now));
                break;
            case R.id.btn_4:
                tv_date.setText(myFmt3.format(now));
                break;
            case R.id.btn_5:
                tv_date.setText(myFmt4.format(now));
                break;
            default:
                break;
        }
    }
}

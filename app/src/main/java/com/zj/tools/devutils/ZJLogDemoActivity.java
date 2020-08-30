package com.zj.tools.devutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zj.tools.mylibrary.ZJLog;

/**
 * ZJLog使用说明
 */
public class ZJLogDemoActivity extends AppCompatActivity implements View.OnClickListener {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private TextView tvContent;
    private Button btPrint;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJLogDemoActivity.class));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_log_demo);
        initView();
    }

    private void initView() {
        tvContent = findViewById(R.id.tv_content);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        btPrint = findViewById(R.id.bt_print);
        btPrint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ZJLog.init(true, "ZJLog");
        ZJLog.d("hello world!");
        ZJLog.d("MyTag", "hello world");
        tvContent.setText("调用如下:\n" +
                "ZJLog.init(true, \"ZJLog\");\n" +
                "ZJLog.d(\"hello world!\");\n" +
                "ZJLog.d(\"MyTag\", \"hello world\");\n" +
                "\n" +
                "结果如下:\n" +
                "D ZJLog   : (ZJLogDemoActivity.java:56)_onClick hello world!\n" +
                "D MyTag   : (ZJLogDemoActivity.java:57)_onClick hello world\n");
    }
}
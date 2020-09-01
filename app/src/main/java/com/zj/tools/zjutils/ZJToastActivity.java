package com.zj.tools.zjutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zj.tools.mylibrary.ZJToast;

public class ZJToastActivity extends AppCompatActivity implements View.OnClickListener {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private Button showToast;
    private TextView tvContent;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJToastActivity.class));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_toast);
        initView();
    }

    private void initView() {
        showToast = findViewById(R.id.show_toast);
        showToast.setOnClickListener(this);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    public void onClick(View v) {

        final StringBuilder text = new StringBuilder();
        text.append("调用代码如下: \n");
        text.append("ZJToast.init(this.getApplication()); \n");
        text.append("ZJToast.show(\"hello world!\"); \n");
        text.append("\n");
        text.append("// ZJToast.show(\"hello world\", Toast.LENGTH_LONG); \n");
        text.append("// ZJToast.showUnSafe(\"hello world\"); \n");
        text.append("// ZJToast.showUnSafe(\"hello world\", Toast.LENGTH_LONG); \n");
        tvContent.setText(text);

        ZJToast.init(this.getApplication());
        ZJToast.show("hello world!");

//        ZJToast.show("hello world", Toast.LENGTH_LONG);
//        ZJToast.showUnSafe("hello world");
//        ZJToast.showUnSafe("hello world", Toast.LENGTH_LONG);
    }
}
package com.zj.tools.zjutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;

import com.zj.tools.mylibrary.ZJNotificationUtils;

public class ZJNotificationUtilsDemoActivity extends AppCompatActivity implements View.OnClickListener {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private Button clickShow;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJNotificationUtilsDemoActivity.class));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_notification_utils_demo);
        initView();
    }

    private void initView() {
        clickShow = findViewById(R.id.click_show);
        clickShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ZJNotificationUtils.show(this, "test title", "test body", R.drawable.ic_launcher);
        final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_test);
        remoteViews.setTextViewText(R.id.tv_title, "title custom");
        remoteViews.setTextViewText(R.id.tv_subtitle, "subtitle custom");
        ZJNotificationUtils.show(this, remoteViews, R.drawable.ic_launcher);
    }
}
package com.zj.tools.zjutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zj.tools.mylibrary.ZJAPKUtils;

import java.io.File;

public class ZJApkUtilsDemoActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected String title() {
        return "ZJApkUtils";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private Button isInstall;
    private Button doInstall;
    private Button getPackageName;
    private TextView tvResult;
    private File apkFile;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJApkUtilsDemoActivity.class));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_apk_utils_demo);
        initView();
    }

    private void initView() {
        apkFile = new File(getExternalFilesDir(null), "1.apk");
        isInstall = findViewById(R.id.is_install);
        doInstall = findViewById(R.id.do_install);
        getPackageName = findViewById(R.id.get_package_name);
        isInstall.setOnClickListener(this);
        doInstall.setOnClickListener(this);
        getPackageName.setOnClickListener(this);
        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {

        if (apkFile == null || !apkFile.exists()) {
            Toast.makeText(this, "apk文件不存在, 请先放置好!", Toast.LENGTH_SHORT).show();
            return;
        }

        final StringBuilder builder = new StringBuilder("结果如下:\n");
        switch (v.getId()) {
            case R.id.is_install:
                final boolean isInstall = ZJAPKUtils.isApkFileInstalled(this, apkFile.getAbsolutePath());
                builder.append(isInstall ? "APK已安装" : "APK未安装");
                tvResult.setText(builder.toString());
                break;
            case R.id.do_install:
                ZJAPKUtils.installApkFile(this, apkFile.getAbsolutePath());
                builder.append("开始调起安装界面...");
                tvResult.setText(builder.toString());
                break;
            case R.id.get_package_name:
                final String packageName = ZJAPKUtils.getPackageName(this, apkFile.getAbsolutePath());
                builder.append("APK包名 = " + packageName);
                tvResult.setText(builder.toString());
                break;
            default:
                break;
        }
    }
}
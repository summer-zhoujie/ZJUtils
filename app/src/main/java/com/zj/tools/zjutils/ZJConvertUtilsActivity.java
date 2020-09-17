package com.zj.tools.zjutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zj.tools.mylibrary.ZJConvertUtils;

public class ZJConvertUtilsActivity extends AppCompatActivity implements View.OnClickListener {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private static final String TAG = "ZJConvertUtilsActivity";
    private EditText etOriginNumber;
    private Button toDp;
    private Button toPx;
    private TextView tvResult;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJConvertUtilsActivity.class));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_convert_utils);
        initView();
    }

    private void initView() {
        etOriginNumber = findViewById(R.id.et_origin_number);
        toDp = findViewById(R.id.to_dp);
        toPx = findViewById(R.id.to_px);
        toDp.setOnClickListener(this);
        toPx.setOnClickListener(this);
        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        final String trim = etOriginNumber.getText().toString().trim();
        int oriValue = -1;
        try {
            oriValue = Integer.parseInt(trim);
        } catch (Exception e) {
            Log.e(TAG, "error, " + Log.getStackTraceString(e));
        }
        if (oriValue < 0) {
            Toast.makeText(this, "请输入一个整数值", Toast.LENGTH_SHORT).show();
            return;
        }

        final StringBuilder builder = new StringBuilder("结果: ");
        switch (v.getId()) {
            case R.id.to_dp:
                builder.append(ZJConvertUtils.px2dp(oriValue) + "");
                break;
            case R.id.to_px:
                builder.append(ZJConvertUtils.dp2px(oriValue) + "");
                break;
            default:
                break;
        }
        tvResult.setText(builder.toString());
    }
}
package com.zj.tools.zjutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zj.tools.mylibrary.ZJHiddenApiUtils;

import java.lang.reflect.Method;

public class ZJHiddenApiUtilsDemoActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected String title() {
        return "ZJHiddenApiUtils";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private Button directInvoke;
    private Button invokeByUtils;
    private TextView tvContent;
    final long SIZE_1_5M = 1024 * 1024 * 1 + 1024 * 500;//1.5M
    private static final String TAG = "ZJHiddenApiUtilsDemoActivity";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // func-out

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJHiddenApiUtilsDemoActivity.class));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_hidden_api_utils_demo);
        initView();
    }

    private void initView() {
        directInvoke = findViewById(R.id.direct_invoke);
        directInvoke.setOnClickListener(this);
        invokeByUtils = findViewById(R.id.invoke_by_utils);
        invokeByUtils.setOnClickListener(this);
        tvContent = findViewById(R.id.tv_content);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.direct_invoke:
                invokeHideMethod();
                break;
            case R.id.invoke_by_utils:
//                ZJHiddenApiUtils.exemptAll();
                ZJHiddenApiUtils.setExemption("Landroid/util/DebugUtils;->sizeValueToString(JLjava/lang/StringBuilder;)Ljava/lang/String;");//;
                invokeHideMethod();
                break;
            default:
                break;
        }
    }

    private void invokeHideMethod() {
        try {
            final Class<?> class_DebugUtils = Class.forName("android.util.DebugUtils");
            final Method m_SizeValueToString = class_DebugUtils.getDeclaredMethod("sizeValueToString", long.class, StringBuilder.class);
            final String result = (String) m_SizeValueToString.invoke(null, SIZE_1_5M, null);
            printResult(result);
        } catch (Exception e) {
            printResult(Log.getStackTraceString(e));
        }
    }

    private void printResult(String result) {
        final StringBuilder builder = new StringBuilder();
        builder.append("调用 android.util.DebugUtils.sizeValueToString(long, StringBuilder)");
        builder.append("求得传入的 bytes = 1024 * 1024 * 1 + 1024 * 500 (≈1.5M) 合适的大小表示字符串");
        builder.append("\n\n");
        builder.append(result);
        tvContent.setText(builder.toString());
    }
}
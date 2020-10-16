package com.zj.tools.zjutils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zj.tools.mylibrary.ZJTextUtils;

import java.util.ArrayList;
import java.util.List;

public class ZJRichTextUtilsDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDisplay;
    private Button btRefresh;
    private EditText etTextcolor;
    private EditText etTextsize;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ZJRichTextUtilsDemoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_j_rich_text);

        tvDisplay = findViewById(R.id.tv_display);
        btRefresh = findViewById(R.id.bt_refresh);
        etTextcolor = findViewById(R.id.et_textcolor);
        etTextsize = findViewById(R.id.et_textsize);

        btRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_refresh:
                dorefresh();
                break;
        }
    }

    private void dorefresh() {
        if (ZJTextUtils.isColorValid(etTextcolor.getText().toString())) {

            final int size = Integer.parseInt(etTextsize.getText().toString());

            final SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(ZJTextUtils.generateSpannableString("hello ", "#000000", 14));
            builder.append(ZJTextUtils.generateSpannableString("world!", "#FF00FF", size));
            tvDisplay.setText(builder);

        } else {
            Toast.makeText(this, "请填入合适的颜色值", Toast.LENGTH_SHORT).show();
        }
    }


}
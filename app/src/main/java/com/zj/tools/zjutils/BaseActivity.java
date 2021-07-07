package com.zj.tools.zjutils;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title());
            actionBar.setDisplayHomeAsUpEnabled(enableBack());
            actionBar.setHomeButtonEnabled(enableBack());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    protected boolean enableBack() {
        return true;
    }

    protected String title() {
        return getString(R.string.app_name);
    }
}

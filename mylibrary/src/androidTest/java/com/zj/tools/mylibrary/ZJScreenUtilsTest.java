package com.zj.tools.mylibrary;

import android.app.Application;
import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZJScreenUtilsTest {

    @Test
    public void test() {
        final Context applicationContext = ApplicationProvider.getApplicationContext();
        assertTrue(ZJScreenUtils.getScreenHeight(applicationContext) > ZJScreenUtils.getScreenWidth(applicationContext));
    }
}
package com.zj.tools.mylibrary;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZJConvertUtilsTest {

    @Test
    public void dp2px() {
        float spValue = 1;
        float px = ZJConvertUtils.sp2px(spValue);
        Log.d("dp2px","px = " + px);
        Assert.assertTrue(spValue <= px);
    }

    @Test
    public void px2dp() {
        float pxValue = 1;
        final float dp = ZJConvertUtils.px2dp(pxValue);
        Log.d("px2dp","dp = " + dp);
        Assert.assertTrue(pxValue >= dp);
    }

    @Test
    public void sp2px() {
        float spValue = 5;
        final int px = ZJConvertUtils.sp2px(spValue);
        Log.d("sp2px","px = " + px);
        Assert.assertTrue(spValue <= px);
    }

    @Test
    public void px2sp() {
        float pxValue = 5;
        final int sp = ZJConvertUtils.px2sp(pxValue);
        Log.d("px2sp","sp = " + sp);
        Assert.assertTrue(pxValue >= sp);
    }
}
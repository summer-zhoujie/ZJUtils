package com.zj.tools.mylibrary;

import android.content.res.Resources;

/**
 * 转换工具
 * <p>
 */
public class ZJConvertUtils {

    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float px2dp(float pxValue) {
        return (pxValue / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int sp2px(float spValue) {
        return (int) (0.5f + spValue * Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static int px2sp(float pxValue) {
        return (int) (0.5f + pxValue / Resources.getSystem().getDisplayMetrics().scaledDensity);
    }
}

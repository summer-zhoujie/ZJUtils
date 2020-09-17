package com.zj.tools.mylibrary;

import android.content.res.Resources;

/**
 * 转换工具
 * <p>
 * github:https://github.com/summer-zhoujie/ZJUtils
 */
class ZJConvertUtils {

    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float px2dp(float pxValue) {
        return (pxValue / Resources.getSystem().getDisplayMetrics().density);
    }
}

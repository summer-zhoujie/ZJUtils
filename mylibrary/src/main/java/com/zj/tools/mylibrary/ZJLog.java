package com.zj.tools.mylibrary;

import android.util.Log;

/**
 * log工具
 */
public class ZJLog {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    /**
     * 是否开启log(默认情况，debug-开，release-关)
     */
    private static boolean enable = false;
    private static String TAG = "ZJLog";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void init(Boolean enable) {
        ZJLog.enable = enable;
    }

    public static void init(Boolean enable, String tag) {
        ZJLog.enable = enable;
        ZJLog.TAG = tag;
    }

    public static void d(String tag, String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.d(tag, stackInfo + msg);
    }

    public static void d(String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.d(TAG, stackInfo + msg);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    private static String getStackInfo() {
        String[] infos = new String[]{"", "", ""};
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length > 4) {
            infos[0] = elements[4].getFileName();
            infos[1] = elements[4].getMethodName();
            infos[2] = String.valueOf(elements[4].getLineNumber());
            return "(" + infos[0] + ":" + infos[2] + ")" + "_" + infos[1] + " ";
        }

        return "";
    }
}

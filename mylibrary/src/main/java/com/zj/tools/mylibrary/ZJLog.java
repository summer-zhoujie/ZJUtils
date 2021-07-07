package com.zj.tools.mylibrary;

import android.app.Application;
import android.util.Log;

/**
 * log工具
 *
 * 先需要初始化, 建议在 {@link Application#onCreate()} 方法中调用 init 方法进行初始化
 * init(true, TAG);
 *
 * 接下来就可以打印log, 目前支持3种
 * e();
 * i();
 * d();
 */
public class ZJLog {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    /**
     * 是否开启log
     */
    private static boolean enable = false;
    private static String TAG = "ZJLog";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    /**
     * 初始化
     * <p>
     * {@link #init(Boolean, String)}
     */
    public static void init(Boolean enable) {
        ZJLog.enable = enable;
    }

    /**
     * 初始化
     *
     * @param enable 是否使能
     * @param tag    TAG
     */
    public static void init(Boolean enable, String tag) {
        ZJLog.enable = enable;
        ZJLog.TAG = tag;
    }

    /**
     * 打印 D 级别的 Log
     *
     * @param tag 指定TAG
     * @param msg msg
     */
    public static void d(String tag, String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.d(tag, stackInfo + msg);
    }

    /**
     * 打印 D 级别的 Log
     * <p>
     * {@link #d(String, String)}
     */
    public static void d(String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.d(TAG, stackInfo + msg);
    }

    /**
     * 打印 E 级别的 Log
     *
     * @param tag 指定TAG
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.e(tag, stackInfo + msg);
    }

    /**
     * 打印 E 级别的 Log
     * <p>
     * {@link #e(String, String)}
     */
    public static void e(String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.e(TAG, stackInfo + msg);
    }

    /**
     * 打印 I 级别的 Log
     *
     * @param tag 指定TAG
     * @param msg msg
     */
    public static void i(String tag, String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.i(tag, stackInfo + msg);
    }

    /**
     * 打印 I 级别的 Log
     * <p>
     * {@link #e(String, String)}
     */
    public static void i(String msg) {
        if (!enable) {
            return;
        }
        String stackInfo = getStackInfo();
        Log.i(TAG, stackInfo + msg);
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

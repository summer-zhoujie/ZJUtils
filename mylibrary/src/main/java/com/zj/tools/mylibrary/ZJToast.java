package com.zj.tools.mylibrary;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 隐藏API(@hide修饰)访问工具
 * <p>
 * 建议在 {@link Application#onCreate()} 方法中调用 init 方法进行初始化
 * <p>
 * github:https://github.com/summer-zhoujie/ZJUtils
 * doc: https://skyjimzhoujie.gitbook.io/zjutils/zjtoast
 */
public class ZJToast {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private static Context context;
    private static Toast toast;
    private static final String TAG = "ZJToast";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    /**
     * 初始化
     * <p>
     * 建议在 {@link Application#onCreate()} 方法中调用 init 方法进行初始化
     */
    public static void init(Application application) {
        context = application;
    }

    /**
     * 展示
     * <p>
     * {@link #show(String, int)}
     */
    public static Toast show(String text) {
        return show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 展示, 会主动捕获任何异常
     *
     * @param text     展示内容
     * @param duration 展示时长 {@link Toast#LENGTH_LONG}
     */
    public static Toast show(String text, int duration) {
        try {
            return doShow(text, duration);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 展示
     * <p>
     * {@link #showUnSafe(String, int)}
     */
    public static Toast showUnSafe(String text) {
        return showUnSafe(text, Toast.LENGTH_SHORT);
    }

    /**
     * 展示, 不会捕获任何异常
     *
     * @param text     展示内容
     * @param duration 展示时长 {@link Toast#LENGTH_LONG}
     */
    public static Toast showUnSafe(String text, int duration) {
        return doShow(text, duration);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func

    private static Toast doShow(String text, int duration) {

        if (context == null) {
            Log.d(TAG, "It may not be initialized, call the initialization code first. example: ZJToast.init(Application)");
            return null;
        }

        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, text, duration);
        toast.show();
        return toast;
    }
}

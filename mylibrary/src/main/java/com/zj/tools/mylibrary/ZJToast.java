package com.zj.tools.mylibrary;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ZJToast {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private static Context context;
    private static Toast toast;
    private static final String TAG = "ZJToast";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static void init(Application application) {
        context = application;
    }

    /**
     * 会主动捕获任何异常
     *
     * @param text
     */
    public static Toast show(String text) {
        return show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 会主动捕获任何异常
     *
     * @param text
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
     * 不会捕获任何异常
     *
     * @param text
     */
    public static Toast showUnSafe(String text) {
        return showUnSafe(text, Toast.LENGTH_SHORT);
    }

    /**
     * 不会捕获任何异常
     *
     * @param text
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

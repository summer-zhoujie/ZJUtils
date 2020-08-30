package com.zj.tools.mylibrary;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * 隐藏API(@hide修饰)访问工具
 * <p>
 * github:https://github.com/summer-zhoujie/ZJUtils
 * doc: https://skyjimzhoujie.gitbook.io/zjutils/zjhiddenapiutils
 */
public class ZJHiddenApiUtils {


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    private static final String TAG = "ZJHiddenApiUtil";
    private static Method sSetHiddenApiExemptions;
    private static Object sVMRuntime;

    static {
        try {
            Method forNameMethod = Class.class.getDeclaredMethod("forName", String.class);
            Method getDeclaredMethodMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);

            Class vmRuntimeClass = (Class) forNameMethod.invoke(null, "dalvik.system.VMRuntime");
            sSetHiddenApiExemptions = (Method) getDeclaredMethodMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
            Method getVMRuntimeMethod = (Method) getDeclaredMethodMethod.invoke(vmRuntimeClass, "getRuntime", null);
            sVMRuntime = getVMRuntimeMethod.invoke(null);
        } catch (Exception e) {
            Log.e(TAG, "static initializer: init error, " + Log.getStackTraceString(e));
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    public static boolean setExemption(String method) {
        return setExemptions(method);
    }

    public static boolean setExemptions(String... methods) {
        if ((sSetHiddenApiExemptions == null)
                || (sVMRuntime == null)) {
            return false;
        }

        try {
            sSetHiddenApiExemptions.invoke(sVMRuntime, new Object[]{methods});
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean exemptAll() {
        return setExemptions("L");
    }
}

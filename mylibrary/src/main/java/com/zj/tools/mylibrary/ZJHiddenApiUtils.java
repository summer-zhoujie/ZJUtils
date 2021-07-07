package com.zj.tools.mylibrary;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * 隐藏API(@hide修饰)访问工具
 *
 * <p>
 *
 *  先使用如下方法设置方法白名单
 *
 *  // 对所有方法设置白名单
 *  ZJHiddenApiUtils.exemptAll();
 *
 *  或者
 *
 *  //  对指定方法设置白名单
 *  //  例如: android.util.DebugUtils.sizeValueToString(long, StringBuilder)
 *  ZJHiddenApiUtils.setExemption("Landroid/util/DebugUtils;->sizeValueToString(JLjava/lang/StringBuilder;)Ljava/lang/String;");
 *
 *  然后开始调用方法(正常的反射流程)
 *
 *  try {
 *             final Class<?> class_DebugUtils = Class.forName("android.util.DebugUtils");
 *             final Method m_SizeValueToString = class_DebugUtils.getDeclaredMethod("sizeValueToString", long.class, StringBuilder.class);
 *             final String result = (String) m_SizeValueToString.invoke(null, SIZE_1_5M, null);
 *             printResult(result);
 *         } catch (Exception e) {
 *             printResult(Log.getStackTraceString(e));
 *  }
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

    /**
     * 设置某个@hide方法可以被访问
     *
     * @param method 单个方法字符串表达(语法: 参考smali)
     *               例如: 描述 android.util.DebugUtils#sizeValueToString ,
     *               "Landroid/util/DebugUtils;->sizeValueToString(JLjava/lang/StringBuilder;)Ljava/lang/String;"
     *               语法参考: https://source.android.com/devices/tech/dalvik/dex-format#fullclassname
     *               查看目录[ 字符串语法 -> TypeDescriptor ]
     * @return 是否修改成功
     */
    public static boolean setExemption(String method) {
        return setExemptions(method);
    }

    /**
     * 设置某些@hide方法可以被访问
     *
     * @param methods 方法字符串列表
     * @return 是否修改成功
     */
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

    /**
     * 设置全部的@hide方法可以被访问
     *
     * @return 是否设置成功
     */
    public static boolean exemptAll() {
        return setExemptions("L");
    }
}

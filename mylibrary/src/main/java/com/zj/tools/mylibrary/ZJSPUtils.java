package com.zj.tools.mylibrary;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link SharedPreferences}辅助类
 * github:https://github.com/summer-zhoujie/ZJUtils
 */
public class ZJSPUtils {

    private static SharedPreferences sharedpreferences = null;

    /**
     * 是否包含某个数据
     */
    public static boolean contains(String k) {
        return getSp().contains(k);
    }

    /**
     * 移除某个数据
     */
    public static boolean remove(String k) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.remove(k);
        return editor.commit();
    }

    /**
     * 保存属性到SP中
     */
    public static boolean putString(String key, String value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 从SP中加载属性
     */
    public static String getString(String key, String defaultValue) {
        String str = getSp().getString(key, defaultValue);
        return str;
    }

    /**
     * 保存属性到SP中
     */
    public static boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 保存属性到SP中
     */
    public static boolean putIntValidToday(String key, int value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 从SP中加载属性
     */
    public static int getInt(String key, int defaultValue) {
        int v = getSp().getInt(key, defaultValue);
        return v;
    }

    /**
     * 保存属性到SP中
     */
    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 从SP中加载属性
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        boolean v = getSp().getBoolean(key, defaultValue);
        return v;
    }


    public static boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static Long getLong(String key, long defaultValue) {
        return getSp().getLong(key, defaultValue);
    }


    public static boolean putFloat(String key, float value) {
        SharedPreferences.Editor edit = getSp().edit();
        return edit.putFloat(key, value).commit();
    }

    public static float getFloat(String key, float defaultValue) {
        return getSp().getFloat(key, defaultValue);
    }

    /**
     * 从SP中加载属性, 仅当天有效
     */
    public static String getStringForToday(String key, String defaultValue) {
        String s = getSp().getString(key, defaultValue);
        final String todayString = "____" + getTodayString() + "____";
        if (s != null && s.startsWith(todayString)) {
            // 有效值
            s = s.replaceFirst(todayString, "");
            return s;
        }

        return defaultValue;
    }

    /**
     * 保存属性到SP中, 仅当天有效
     */
    public static boolean putStringForToday(String key, String value) {
        SharedPreferences.Editor editor = getSp().edit();
        final String todayString = "____" + getTodayString() + "____";
        editor.putString(key, todayString + value);
        return editor.commit();
    }

    private static String getTodayString() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    private static SharedPreferences getSp() {
        return sharedpreferences;
    }

    public static void initSp(Application application) {
        if (sharedpreferences != null) {
            return;
        }
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

}

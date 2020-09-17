package com.zj.tools.mylibrary;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


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
     * 从SP中加载属性, 仅当天有效
     */
    public static String getStringValidToday(String key, String defaultValue) {
        final String s = getSp().getString(key, defaultValue);
        final String todayString = getTodayString();
        if (TextUtils.isEmpty(s) || TextUtils.equals(defaultValue, s)) {
            putStringValidToday(key, defaultValue);
            return defaultValue;
        }

        final String[] split = s.split(",");
        if (split.length == 2) {
            if (todayString.equals(split[0])) {
                return split[1];
            } else {
                putStringValidToday(key, defaultValue);
                return defaultValue;
            }
        }

        putStringValidToday(key, defaultValue);
        return defaultValue;
    }

    /**
     * 保存属性到SP中, 仅当天有效
     */
    public static boolean putStringValidToday(String key, String value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putString(key, getTodayString() + "," + value);
        return editor.commit();
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

    private static String getTodayString() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    private static SharedPreferences getSp() {
        return sharedpreferences;
    }

    private static void initSp(Application application) {
        if (sharedpreferences != null) {
            return;
        }
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

}

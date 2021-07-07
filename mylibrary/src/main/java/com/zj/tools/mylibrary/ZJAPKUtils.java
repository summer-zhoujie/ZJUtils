package com.zj.tools.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.HashMap;

/**
 * apk文件工具
 *
 * 1. apk文件是否安装
 * 2. 调起apk文件的安装
 * 3. 获取apk文件的包名
 */
public class ZJAPKUtils {

    /**
     * apk文件是否安装
     *
     * @param path    apk文件路径
     * @param context 用于获取{@link PackageManager}
     * @return true:已安装
     */
    public static boolean isApkFileInstalled(Context context, String path) {

        if (TextUtils.isEmpty(path) || context == null) {
            return false;
        }

        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        boolean installed = false;
        if (info != null) {
            try {
                pm.getPackageInfo(info.packageName, PackageManager.GET_ACTIVITIES);
                installed = true;
            } catch (PackageManager.NameNotFoundException e) {
                installed = false;
            }
        }
        return installed;
    }

    /**
     * 调起安装APK文件
     *
     * 需要在Manifest中集成以下内容
     *
     * <provider
     *  android:name="androidx.core.content.FileProvider"
     *  android:authorities="com.zj.tools.mylibrary.fileprovider"
     *  android:exported="false"
     *  android:grantUriPermissions="true">
     *      <meta-data
     *      android:name="android.support.FILE_PROVIDER_PATHS"
     *      android:resource="@xml/pg_file_path"
     *      tools:replace="android:resource" />
     * </provider>
     *
     * 再创建xml文件, 内容如下
     *
     *  <?xml version="1.0" encoding="utf-8"?>
     *  <paths>
     *  <!--  APKUtils  这里填入自己APK文件放置的路径即可-->
     *      <external-files-path
     *          name="external_files_path"
     *          path="Download" />
     *  </paths>
     *
     * @param path apk文件路径
     */
    public static void installApkFile(Context context, String path) {

        File file = new File(path);
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            try {
                String packageName = info.packageName;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    installApp(context, file);
                } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                    start7Install(context, file);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (!pm.canRequestPackageInstalls()) {
                        //打开权限
                        Uri packageURI = Uri.parse("package:" + packageName);
                        //注意这个是8.0新API
                        Intent intent1 = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                        context.startActivity(intent1);
                        start7Install(context, file);
                    } else {
                        //有权限，直接安装
                        start7Install(context, file);
                    }
                }

            } catch (Exception e) {
                Log.e("ZJAPKUtils", "error = " + Log.getStackTraceString(e));
            }
        }
    }

    private static void start7Install(Context context, File file) {
        Uri apkUri = FileProvider.getUriForFile(context, "com.zj.tools.mylibrary.fileprovider", file);
        //在AndroidManifest中的android:authorities值
        Intent install = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        context.startActivity(install);
    }

    private static void installApp(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取安装文件包名
     *
     * @param path apk filepath
     * @return packagename
     */
    public static String getPackageName(Context context, String path) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.packageName;
        }
        return null;
    }
}

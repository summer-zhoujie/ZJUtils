package com.zj.tools.mylibrary;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

/**
 * 通知工具
 * <p>
 * github:https://github.com/summer-zhoujie/ZJUtils
 */
public class ZJNotificationUtils {

    private static final String TAG = "ZJNotificationUtils";
    public static final String DEFAULT_CHANNEL_ID = "zj_default_channel_id";
    public static final String DEFAULT_CHANNEL_NAME = "通用";
    public static final long[] DEFAULT_VIBRATION_PATTERN = new long[]{100, 200, 300};
    public static final int DEFAULT_COLOR = Color.GREEN;
    public static final boolean DEFAULT_ENABLE_VIBRATION = true;
    public static final boolean DEFAULT_ENABLE_SOUND = true;
    private static int baseNotifId = 1000;


    public static void show(Context context, String title, String body, int smallIconId) {
        int notifId = baseNotifId + 1;
        show(context, smallIconId, body, title, null,
                notifId, null, null, DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME,
                null, DEFAULT_VIBRATION_PATTERN, DEFAULT_COLOR, DEFAULT_ENABLE_VIBRATION, DEFAULT_ENABLE_SOUND);
    }

    public static void show(Context context, RemoteViews remoteViews, int smallIconId) {
        int notifId = baseNotifId + 1;
        show(context, smallIconId, null, null, null,
                notifId, null, null, DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME,
                remoteViews, DEFAULT_VIBRATION_PATTERN, DEFAULT_COLOR, DEFAULT_ENABLE_VIBRATION, DEFAULT_ENABLE_SOUND);
    }

    /**
     * 展示notification
     */
    public static void show(Context context, int smallIconId, String body, String title, PendingIntent pendingIntent, int notifId, String groupId, String groupMsg, String channelId, String channelName, RemoteViews remoteViews, long[] vibrationPattern, int lightColor, boolean enableVibration, boolean enableSound) {
        if (context == null) {
            Log.e(TAG, "params error, context == null");
            return;
        }

        if (smallIconId < 0) {
            Log.e(TAG, "params error, smallIconId < 0");
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && TextUtils.isEmpty(channelId) && TextUtils.isEmpty(channelName)) {
            Log.e(TAG, "params error, when SDK_INT > Build.VERSION_CODES.O, 'channelId' and 'channelName' can not be null or empty, please check again");
            return;
        }

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            if (enableVibration && vibrationPattern != null && vibrationPattern.length > 0) {
                notificationChannel.setVibrationPattern(vibrationPattern);
            }
            notificationChannel.enableVibration(enableVibration);
            if (lightColor > 0) {
                notificationChannel.setLightColor(lightColor);
                notificationChannel.enableLights(true);
            }
            if (!TextUtils.isEmpty(groupId) && !TextUtils.isEmpty(groupMsg)) {
                NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(groupId, groupMsg);
                mNotificationManager.createNotificationChannelGroup(notificationChannelGroup);
                notificationChannel.setGroup(groupId);
            }
            mNotificationManager.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(smallIconId)
                    .setTicker(body)
                    .setContentText(body)
                    .setContentTitle(title)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)
                    .setOngoing(false);
            if (remoteViews != null) {
                builder.setCustomContentView(remoteViews);
            }
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            mNotificationManager.notify(notifId, builder.build());
        } else {
            if (TextUtils.isEmpty(channelId)) {
                Log.e(TAG, "params error, channelId == null");
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(smallIconId)
                    .setTicker(body)
                    .setContentText(body)
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true);

            if (remoteViews != null) {
                builder.setCustomContentView(remoteViews);
            }
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            if (enableSound) {
                builder.setDefaults(Notification.DEFAULT_SOUND);
            }
            if (enableVibration) {
                builder.setDefaults(Notification.DEFAULT_VIBRATE);
            }
            if (vibrationPattern != null && enableVibration) {
                builder.setVibrate(vibrationPattern);
            }
            builder.setPriority(Notification.PRIORITY_HIGH);
            Notification notification = builder.build();
            notification.flags = Notification.FLAG_SHOW_LIGHTS;
            if (lightColor > 0) {
                notification.ledARGB = lightColor; // LED灯的颜色，绿灯
            }
            mNotificationManager.notify(notifId, notification);
        }
    }
}

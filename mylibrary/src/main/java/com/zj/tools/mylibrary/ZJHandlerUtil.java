package com.zj.tools.mylibrary;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Handler便捷工具
 *
 * 1. 主线程 Handler
 * 2. 主线程延时 Handler
 * 3. 移除主线程 Handler的Runnable
 * 4. 子线程 Handler
 * 5. 子线程延时 Handler
 * 6. 移除子线程 Handler的Runnable
 */
public class ZJHandlerUtil {

    private static final Handler MAIN_LOOPER = new Handler(Looper.getMainLooper());
    private static Handler BACKGROUND_LOOPER = null;

    /**
     * 主线程 Handler
     * @param run
     */
    public synchronized static void postToMain(final Runnable run) {
        MAIN_LOOPER.post(run);
    }

    /**
     * 主线程延时 Handler
     * @param run
     * @param delayMillis 延时, 单位: ms
     */
    public synchronized static void postToMain(final Runnable run, final long delayMillis) {
        MAIN_LOOPER.postDelayed(run, delayMillis);
    }

    /**
     * 移除主线程 Handler的Runnable
     * @param run
     */
    public synchronized static void removeTaskFromMan(final Runnable run) {
        MAIN_LOOPER.removeCallbacks(run);
    }

    /**
     * 子线程 Handler
     * @param run
     */
    public synchronized static void postToBackground(final Runnable run) {
        postToBackground(run, 0);
    }

    /**
     * 子线程延时 Handler
     * @param run
     * @param delayMillis
     */
    public synchronized static void postToBackground(final Runnable run, final long delayMillis) {
        if (BACKGROUND_LOOPER == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            BACKGROUND_LOOPER = new Handler(thread.getLooper());
        }

        BACKGROUND_LOOPER.postDelayed(run, delayMillis);
    }

    /**
     * 移除子线程 Handler的Runnable
     * @param run
     */
    public synchronized static void removeTaskFromBackground(final Runnable run) {
        if (BACKGROUND_LOOPER != null) {
            BACKGROUND_LOOPER.removeCallbacks(run);
        }
    }

}

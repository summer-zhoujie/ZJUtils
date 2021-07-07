package com.zj.tools.mylibrary;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ZJHandlerUtilTest {

    public static final String TAG = "ZJHandlerUtilTest";


    @Test
    public void postToMain() throws InterruptedException {
        final String[] ThreadName = {""};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        //>>>>>>>>>>>>>>>>>>>>关键代码<<<<<<<<<<<<<<<<<<<<<<<
        ZJHandlerUtil.postToMain(new Runnable() {
            @Override
            public void run() {
                ThreadName[0] = Thread.currentThread().getName();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(2, TimeUnit.SECONDS);
        Log.d(TAG, "postToMain: ThreadName = " + ThreadName[0]);
        Assert.assertEquals(ThreadName[0], "main");
    }

    @Test
    public void testPostToMain() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final long duration = 1000;
        final long startTime = System.currentTimeMillis();
        final long[] endTime = {startTime};
        final String[] ThreadName = {""};
        //>>>>>>>>>>>>>>>>>>>>关键代码<<<<<<<<<<<<<<<<<<<<<<<
        ZJHandlerUtil.postToMain(new Runnable() {
            @Override
            public void run() {
                endTime[0] = System.currentTimeMillis();
                ThreadName[0] = Thread.currentThread().getName();
                countDownLatch.countDown();
            }
        }, duration);

        countDownLatch.await(2, TimeUnit.SECONDS);

        final long realDuration = endTime[0] - startTime;
        Log.d(TAG, "realDuration: " + realDuration + ", ThreadName = " + ThreadName[0]);
        Assert.assertEquals(ThreadName[0], "main");
        Assert.assertTrue(realDuration >= duration && realDuration <= duration + 50);
    }

    @Test
    public void removeTaskFromMan() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final long duration = 1000;
        final long startTime = System.currentTimeMillis();
        final long[] endTime = {startTime};
        final String[] ThreadName = {"NOT_RUN"};
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                endTime[0] = System.currentTimeMillis();
                ThreadName[0] = Thread.currentThread().getName();
                countDownLatch.countDown();
            }
        };
        ZJHandlerUtil.postToMain(run, duration);
        //>>>>>>>>>>>>>>>>>>>>关键代码<<<<<<<<<<<<<<<<<<<<<<<
        ZJHandlerUtil.removeTaskFromMan(run);
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "removeTaskFromMan: e= " + Log.getStackTraceString(e));
        }

        final long realDuration = endTime[0] - startTime;
        Log.d(TAG, "realDuration: " + realDuration + ", ThreadName = " + ThreadName[0]);
        Assert.assertEquals(ThreadName[0], "NOT_RUN");
        assertEquals(0, realDuration);
    }

    @Test
    public void postToBackground() throws InterruptedException {
        final String[] ThreadName = {""};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        //>>>>>>>>>>>>>>>>>>>>关键代码<<<<<<<<<<<<<<<<<<<<<<<
        ZJHandlerUtil.postToBackground(new Runnable() {
            @Override
            public void run() {
                ThreadName[0] = Thread.currentThread().getName();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(2, TimeUnit.SECONDS);
        Log.d(TAG, "postToMain: ThreadName = " + ThreadName[0]);
        Assert.assertNotEquals(ThreadName[0], "main");
    }

    @Test
    public void testPostToBackground() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final long duration = 3000;
        final long startTime = System.currentTimeMillis();
        final long[] endTime = {startTime};
        final String[] ThreadName = {""};
        //>>>>>>>>>>>>>>>>>>>>关键代码<<<<<<<<<<<<<<<<<<<<<<<
        ZJHandlerUtil.postToBackground(new Runnable() {
            @Override
            public void run() {
                endTime[0] = System.currentTimeMillis();
                ThreadName[0] = Thread.currentThread().getName();
                countDownLatch.countDown();
            }
        }, duration);

        countDownLatch.await(6000, TimeUnit.SECONDS);

        final long realDuration = endTime[0] - startTime;
        Log.d(TAG, "realDuration: " + realDuration + ", ThreadName = " + ThreadName[0]);
        Assert.assertNotEquals(ThreadName[0], "main");
        Assert.assertTrue(realDuration >= duration && realDuration <= duration + 50);
    }

    @Test
    public void removeTaskFromBackground() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final long duration = 1000;
        final long startTime = System.currentTimeMillis();
        final long[] endTime = {startTime};
        final String[] ThreadName = {"NOT_RUN"};
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                endTime[0] = System.currentTimeMillis();
                ThreadName[0] = Thread.currentThread().getName();
                countDownLatch.countDown();
            }
        };
        ZJHandlerUtil.postToBackground(run, duration);
        //>>>>>>>>>>>>>>>>>>>>关键代码<<<<<<<<<<<<<<<<<<<<<<<
        ZJHandlerUtil.removeTaskFromBackground(run);
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "removeTaskFromBackground: e= " + Log.getStackTraceString(e));
        }

        final long realDuration = endTime[0] - startTime;
        Log.d(TAG, "realDuration: " + realDuration + ", ThreadName = " + ThreadName[0]);
        Assert.assertEquals(ThreadName[0], "NOT_RUN");
        assertEquals(0, realDuration);
    }
}
package com.zj.tools.mylibrary;

import android.app.Application;


import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class ZJSPUtilsTest {

    @Before
    public void initSp() {
        ZJSPUtils.initSp((Application) ApplicationProvider.getApplicationContext());
        ZJSPUtils.clear();
    }

    @Test
    public void contains() {
        final String KEY = "contains_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putBoolean(KEY, true);
        assertTrue(ZJSPUtils.contains(KEY));
    }

    @Test
    public void remove() {
        final String KEY = "remove_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putBoolean(KEY, true);
        assertTrue(ZJSPUtils.contains(KEY));
        ZJSPUtils.remove(KEY);
        assertFalse(ZJSPUtils.contains(KEY));
    }

    @Test
    public void putAndGetString() {
        final String KEY = "string_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putString(KEY, "string");
        assertTrue(ZJSPUtils.contains(KEY));
        assertEquals(ZJSPUtils.getString(KEY, "default"), "string");
    }

    @Test
    public void putAndGetInt() {
        final String KEY = "int_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putInt(KEY, 10);
        assertTrue(ZJSPUtils.contains(KEY));
        assertEquals(ZJSPUtils.getInt(KEY, 0), 10);
    }

    @Test
    public void putAndGetBoolean() {
        final String KEY = "boolean_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putBoolean(KEY, true);
        assertTrue(ZJSPUtils.contains(KEY));
        assertTrue(ZJSPUtils.getBoolean(KEY, false));
    }

    @Test
    public void putAndGetLong() {
        final String KEY = "long_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putLong(KEY, 1);
        assertTrue(ZJSPUtils.contains(KEY));
        assertTrue(ZJSPUtils.getLong(KEY, 5) - 1 == 0);
    }

    @Test
    public void putAndGetFloat() {
        final String KEY = "Float_key_test";
        assertFalse(ZJSPUtils.contains(KEY));
        ZJSPUtils.putFloat(KEY, 1);
        assertTrue(ZJSPUtils.contains(KEY));
        assertTrue(ZJSPUtils.getFloat(KEY, 5) - 1 == 0);
    }
}
package com.zj.tools.mylibrary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZJLogTest {

    @Before
    public void init() {
        ZJLog.init(true, "ZJLog");
    }

    @Test
    public void d() {
        ZJLog.d("d test");
    }

    @Test
    public void e() {
        ZJLog.d("e test");
    }

    @Test
    public void i() {
        ZJLog.d("i test");
    }

    @Test
    public void v() {
        ZJLog.v("v test");
    }

    @Test
    public void w() {
        ZJLog.w("w test");
    }
}
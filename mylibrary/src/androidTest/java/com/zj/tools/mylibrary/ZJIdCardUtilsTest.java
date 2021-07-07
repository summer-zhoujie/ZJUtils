package com.zj.tools.mylibrary;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ZJIdCardUtilsTest {

    /**
     * 测试号码
     * <p>
     * 生日: 1976年7月17日
     * <p>
     * 性别: 男
     */
    public static final String Id_CARD_TEST = "350723197607170810";

    @Test
    public void isIdCardValid() {
        assertTrue(ZJIdCardUtils.isIdCardValid(Id_CARD_TEST));
    }

    @Test
    public void getGender() {
        assertEquals(ZJIdCardUtils.getGender(Id_CARD_TEST), 1);
    }

    @Test
    public void getAge() {
        final SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
        final Date date = new Date();
        final Integer curYear = Integer.valueOf(yyyy.format(date));

        int age = curYear - 1976 - 1;

        assertEquals(ZJIdCardUtils.getAge(Id_CARD_TEST), age);
    }
}
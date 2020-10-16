package com.zj.tools.mylibrary;

import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.Nullable;


/**
 * 辅助{@link android.widget.TextView}, 拼接大小, 颜色,不同的字符串
 * <p>
 * github:https://github.com/summer-zhoujie/ZJUtils
 */
public class ZJTextUtils {

    /**
     * 辅助生成{@link android.widget.TextView#setText(CharSequence)}, 支持设置颜色, 大小
     * <p>
     * 用法参考
     * final SpannableStringBuilder builder = new SpannableStringBuilder();
     * builder.append(ZJTextUtils.generateSpannableString("hello ", "#000000", 14));
     * builder.append(ZJTextUtils.generateSpannableString("world!", "#FF00FF", 25));
     * textView.setText(builder);
     *
     * @param text  文字
     * @param color 文字颜色(例如: #FF00FF)
     * @param size  文字大小(单位: sp)
     * @return {@link SpannableString}
     */
    public static SpannableString generateSpannableString(@Nullable String text, @Nullable String color, @Nullable Integer size) {

        if (text == null) {
            return null;
        }
        if (TextUtils.isEmpty(text)) {
            return new SpannableString(text);
        }

        final SpannableString itemString = new SpannableString(text);
        if (!TextUtils.isEmpty(color)) {
            itemString.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, itemString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        if (size != null) {
            final int sp = (int) (0.5f + size * Resources.getSystem().getDisplayMetrics().scaledDensity);
            itemString.setSpan(new AbsoluteSizeSpan(sp), 0, itemString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return itemString;
    }

    /**
     * 传入的颜色是是否合规
     *
     * @param colorString 颜色字符串 (例: #FFFFFF)
     * @return true, 颜色字符串合规
     */
    public static boolean isColorValid(String colorString) {
        if (TextUtils.isEmpty(colorString)) {
            return false;
        }

        try {
            Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }
}

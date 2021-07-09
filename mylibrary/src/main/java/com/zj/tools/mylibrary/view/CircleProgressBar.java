package com.zj.tools.mylibrary.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zj.tools.mylibrary.R;

public class CircleProgressBar extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // variables

    /**
     * 最大值(目标值)
     */
    private int max = 100;
    /**
     * 当前进度值
     */
    private int progress = 0;
    /**
     * 进度条的背景色(当设置了渐变,则此属性无效)
     */
    private int progressColor = 0;
    /**
     * 进度条的渐变背景色(start)
     */
    private int progressColorStart = 0;
    /**
     * 进度条的渐变背景色(mid)
     */
    private int progressColorMid = 0;
    /**
     * 进度条的渐变背景色(end)
     */
    private int progressColorEnd = 0;
    /**
     * 进度条开始的角度
     * <p>
     * (上: 270°, 下: 90°, 左: 180°, 右: 0°)
     */
    private int startAngle = 270;
    /**
     * 定义轨道背景弧形区域绘制的百分比(是满的圆形进度条,还是半圆弧的进度条)[默认是圆形1.0f]
     */
    private float circlePercent = 1.0f;
    /**
     * 轨道背景的绘制弧长
     */
    private float bgSweepAngle;
    /**
     * 进度条的绘制弧长
     */
    private float progressSweepAngle = 0;
    /**
     * 轨道背景的颜色(设置了渐变色后,此属性无效)
     */
    private int progressBgColor = 0;
    /**
     * 轨道背景的渐变颜色(start)
     */
    private int progressBgColorStart = 0;
    /**
     * 轨道背景的渐变颜色(mid)
     */
    private int progressBgColorMid = 0;
    /**
     * 轨道背景的渐变颜色(end)
     */
    private int progressBgColorEnd = 0;
    /**
     * 进度条的宽度
     */
    private float strokeWidth = dp2px(7);
    /**
     * 进度条内环所占的矩形区域(需要减去圆环的宽度)
     */
    private RectF progressCircleRect = null;
    /**
     * 绘制进度轨道的画笔
     */
    private Paint progressBgPaint;
    /**
     * 绘制进度的画笔
     */
    private Paint progressPaint;
    /**
     * 绘制thumb的画笔
     */
    private Paint progressThumbPaint;
    /**
     * 记录thumb的图像
     */
    private Bitmap thumb;
    /**
     * 缩略图的大小
     */
    private float thumbWidth;
    /**
     * 当前画布的宽高(并不一定等于空间的宽高)
     */
    private int w = -1;
    private int h = -1;
    /**
     * 存储计算后的thumb的中心点
     */
    private Point mThumbPoint = new Point();
    /**
     * thumb图片(不传不显示)
     */
    private int thumbSrc = 0;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // in-func
    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttrs(context, attrs);
        init();
    }

    /**
     * 解析xml传入的属性值
     */
    private void parseAttrs(Context context, @Nullable AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CirProgressBar, 0, 0);
        try {
            this.max = a.getInteger(R.styleable.CirProgressBar_max, this.max);
            this.progress = a.getInteger(R.styleable.CirProgressBar_progress, this.progress);

            // 进度条属性值
            this.progressColor = a.getColor(R.styleable.CirProgressBar_progress_color, this.progressColor);
            this.progressColorStart = a.getColor(R.styleable.CirProgressBar_progress_color_start, this.progressColorStart);
            this.progressColorMid = a.getColor(R.styleable.CirProgressBar_progress_color_mid, this.progressColorMid);
            this.progressColorEnd = a.getColor(R.styleable.CirProgressBar_progress_color_end, this.progressColorEnd);

            // 进度条轨道属性值
            this.progressBgColor = a.getColor(R.styleable.CirProgressBar_progress_bg_color, this.progressBgColor);
            this.progressBgColorStart = a.getColor(R.styleable.CirProgressBar_progress_bg_color_start, this.progressBgColorStart);
            this.progressBgColorMid = a.getColor(R.styleable.CirProgressBar_progress_bg_color_mid, this.progressBgColorMid);
            this.progressBgColorEnd = a.getColor(R.styleable.CirProgressBar_progress_bg_color_end, this.progressBgColorEnd);

            // 定义进度是完整的圆形, 还是一段圆弧
            this.circlePercent = Math.min(a.getFloat(R.styleable.CirProgressBar_progress_circle_percent, this.circlePercent), 1.0f);
            this.circlePercent = Math.max(this.circlePercent, 0.0f);
            this.startAngle = a.getInteger(R.styleable.CirProgressBar_progress_start_angle, this.startAngle);

            this.strokeWidth = a.getDimension(R.styleable.CirProgressBar_progress_stroke_width, this.strokeWidth);
            this.thumbWidth = a.getDimension(R.styleable.CirProgressBar_progress_thumb_width, this.thumbWidth);

            this.thumbSrc = a.getResourceId(R.styleable.CirProgressBar_progress_thumb_src, this.thumbSrc);
        } finally {
            a.recycle();
        }
    }

    private void init() {

        progressBgPaint = new Paint();
        progressBgPaint.setAntiAlias(true);
        progressBgPaint.setStyle(Paint.Style.STROKE);
        progressBgPaint.setStrokeWidth(strokeWidth);
        progressBgPaint.setStrokeCap(Paint.Cap.ROUND);

        // 初始化进度条的画笔
        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        progressThumbPaint = new Paint();
        progressThumbPaint.setAntiAlias(true);
        progressThumbPaint.setStyle(Paint.Style.FILL);

        //对单独的View在运行时阶段禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        updateThumb();
    }

    /**
     * 更新缩略图
     */
    private void updateThumb() {
        if (thumbSrc != 0) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), thumbSrc, options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = culBitmapSampleSize((int) thumbWidth, (int) thumbWidth, options.outWidth, options.outHeight);
            thumb = BitmapFactory.decodeResource(getResources(), thumbSrc, options);
        } else {
            thumb = null;
        }
    }

    /**
     * 计算thumb图片的缩放比例
     *
     * @param inWidth   目标宽
     * @param inHeight  目标高
     * @param outWidth  实际图宽
     * @param outHeight 实际图高
     * @return 缩放比例
     */
    private int culBitmapSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
        final int sampleSize = Math.min(outHeight / inHeight, outWidth / inWidth);

        // 取一个最大的 且  <= exactSampleSize 且 是2的次方
        final int powerOfTwoSampleSize = sampleSize == 0 ? 0 :
                Integer.highestOneBit(sampleSize);

        // powerOfTwoSampleSize == 0 代表不缩放,也就是返回 1 倍
        return Math.max(1, powerOfTwoSampleSize * 2);
    }

    /**
     * 更新进度条画笔的颜色
     *
     * @param pivotX             绘制进度条时的圆心X坐标, 用来绘制渐变色
     * @param pivotY             绘制进度条时的圆心Y坐标
     * @param progressSweepAngle 进度圆环的绘制弧长
     */
    private void updateProgressPaintColor(float pivotX, float pivotY, float progressSweepAngle) {
        if (progressPaint != null) {
            if (this.progressColorStart != 0
                    || this.progressColorMid != 0
                    || this.progressColorEnd != 0) {
                int[] colors = {this.progressColorStart, this.progressColorMid, this.progressColorEnd, this.progressColorStart};
                float distance = progressSweepAngle / 360;
                float[] position = {0.0f, distance * 0.5f, distance, distance + 0.1f};
                final SweepGradient sweepGradient = new SweepGradient(pivotX, pivotY, colors, position);
                progressPaint.setShader(sweepGradient);
            } else {
                progressPaint.setShader(null);
                progressPaint.setColor(this.progressColor);
            }
        }
    }

    /**
     * 更新进度轨道条画笔的颜色
     *
     * @param pivotX               绘制进度条轨道时的圆心X坐标, 用来绘制渐变色
     * @param pivotY               绘制进度条轨道时的圆心Y坐标
     * @param progressBgSweepAngle 进度圆环轨道的绘制弧长
     */
    private void updateProgressBgPaintColor(float pivotX, float pivotY, float progressBgSweepAngle) {
        if (progressBgPaint != null) {
            if (this.progressBgColorStart != 0
                    || this.progressBgColorMid != 0
                    || this.progressBgColorEnd != 0) {
                int[] colors = {this.progressBgColorStart, this.progressBgColorMid, this.progressBgColorEnd, this.progressBgColorStart};
                float distance = progressBgSweepAngle / 360;
                float[] position = {0.0f, distance * 0.5f, distance, distance + 0.1f};
                final SweepGradient sweepGradient = new SweepGradient(pivotX, pivotY, colors, position);
                progressBgPaint.setShader(sweepGradient);
            } else {
                progressBgPaint.setShader(null);
                progressBgPaint.setColor(this.progressBgColor);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        // 计算圆环内环所占的矩形区域
        updateCircleRectF(w, h);
        culDrawProgressParamsAgain();
    }

    /**
     * 更新圆环进度的矩形区域
     */
    private void updateCircleRectF(int w, int h) {
        final int diff = h - w;
        final float padding = Math.max(strokeWidth / 2, thumbWidth / 2);
        final float circleRectL = diff >= 0 ? padding : padding + (-diff * 1.0f / 2);
        final float circleRectT = diff <= 0 ? padding : padding + (diff * 1.0f / 2);
        final float circleRectR = diff >= 0 ? w - padding : w - padding - (-diff * 1.0f / 2);
        final float circleRectB = diff <= 0 ? h - padding : h - padding - (diff * 1.0f / 2);
        progressCircleRect = new RectF(circleRectL, circleRectT, circleRectR, circleRectB);
    }

    /**
     * 重新计算绘制[进度]相关的参数
     */
    private void culDrawProgressParamsAgain() {

        // 圆心
        final int pivotX = getCirclePivotX();
        final int pivotY = getCirclePivotY();

        // 计算进度和背景圆弧的长度
        bgSweepAngle = circlePercent * 360;
        progressSweepAngle = bgSweepAngle * culCurProgress();

        // 计算绘制进度条和进度轨道的颜色参数
        updateProgressPaintColor(pivotX, pivotY, progressSweepAngle);
        updateProgressBgPaintColor(pivotX, pivotY, bgSweepAngle);

        // 计算thumb的绘制坐标
        float mRingRadius = (progressCircleRect.right - progressCircleRect.left) / 2;
        float angle = startAngle + culCurProgress() * bgSweepAngle; // 进度换算角度angle
        angle = angle % 360;
        angle = angle < 180 ? angle : angle - 360;
        double angleR = (angle / 180.0f) * Math.PI;
        mThumbPoint.x = (int) (pivotX + mRingRadius * Math.cos(angleR) - thumbWidth / 2);
        mThumbPoint.y = (int) (pivotY + mRingRadius * Math.sin(angleR) - thumbWidth / 2);
        // 加入缩放图片产生的距离偏差
        if (thumb != null) {
            mThumbPoint.x += (thumbWidth - thumb.getWidth()) / 2;
            mThumbPoint.y += (thumbWidth - thumb.getHeight()) / 2;
        }
    }

    private int getCirclePivotX() {
        return w / 2;
    }

    private int getCirclePivotY() {
        return h / 2;
    }

    /**
     * 计算当前的进度
     *
     * @return
     */
    private float culCurProgress() {
        if (max == 0 || progress == 0) {
            return 0;
        }
        float result = Math.min(progress * 1.0f / max, 1.0f);
        return Math.max(result, 0.0f);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (h != -1 && w != -1 && progressCircleRect != null) {
            canvas.save();
            canvas.rotate(startAngle, getCirclePivotX(), getCirclePivotY());
            canvas.drawArc(progressCircleRect, 0, bgSweepAngle, false, progressBgPaint);
            canvas.drawArc(progressCircleRect, 0, progressSweepAngle, false, progressPaint);
            canvas.restore();
            if (thumb != null) {
                canvas.drawBitmap(thumb, mThumbPoint.x, mThumbPoint.y, progressThumbPaint);
            }
        }
    }

    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // out-func

    /**
     * 更新目标值
     */
    public void setMax(int max) {
        this.max = max;
        culDrawProgressParamsAgain();
        postInvalidateOnAnimation();
    }

    /**
     * 更新进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        culDrawProgressParamsAgain();
        postInvalidateOnAnimation();
    }


    /**
     * 更新背景轨道颜色 [ 非渐变 ]
     */
    public void setBgColor(int color) {
        progressBgColor = color;
        updateProgressBgPaintColor(getCirclePivotX(), getCirclePivotY(), bgSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新背景轨道颜色 [ 渐变 ]
     */
    public void setBgColorStart(int color) {
        progressBgColorStart = color;
        updateProgressBgPaintColor(getCirclePivotX(), getCirclePivotY(), bgSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新背景轨道颜色 [ 渐变 ]
     */
    public void setBgColorMid(int color) {
        progressBgColorMid = color;
        updateProgressBgPaintColor(getCirclePivotX(), getCirclePivotY(), bgSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新背景轨道颜色 [ 渐变 ]
     */
    public void setBgColorEnd(int color) {
        progressBgColorEnd = color;
        updateProgressBgPaintColor(getCirclePivotX(), getCirclePivotY(), bgSweepAngle);
        postInvalidateOnAnimation();
    }


    /**
     * 更新进度颜色 [ 非渐变 ]
     */
    public void setProgressColor(int color) {
        progressColor = color;
        updateProgressPaintColor(getCirclePivotX(), getCirclePivotY(), progressSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新进度颜色 [ 渐变 ]
     */
    public void setProgressColorStart(int color) {
        progressColorStart = color;
        updateProgressPaintColor(getCirclePivotX(), getCirclePivotY(), progressSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新进度颜色 [ 渐变 ]
     */
    public void setProgressColorMid(int color) {
        progressColorMid = color;
        updateProgressPaintColor(getCirclePivotX(), getCirclePivotY(), progressSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新进度颜色 [ 渐变 ]
     */
    public void setProgressColorEnd(int color) {
        progressColorEnd = color;
        updateProgressPaintColor(getCirclePivotX(), getCirclePivotY(), progressSweepAngle);
        postInvalidateOnAnimation();
    }

    /**
     * 更新起始角度
     *
     * @param degrees 度数 0~360
     */
    public void setStartAngle(int degrees) {
        startAngle = degrees;
        postInvalidateOnAnimation();
    }

    /**
     * 设置圆弧比例
     *
     * @param percent 比例(0~1)
     */
    public void setCirclePercent(float percent) {
        float newValue = Math.max(0, percent);
        newValue = Math.min(1, newValue);
        circlePercent = newValue;
        culDrawProgressParamsAgain();
        postInvalidateOnAnimation();
    }

    /**
     * 设置轨道宽度
     *
     * @param width 单位dp
     */
    public void setStrokeWidth(int width) {
        strokeWidth = dp2px(width);
        progressBgPaint.setStrokeWidth(strokeWidth);
        progressPaint.setStrokeWidth(strokeWidth);
        postInvalidateOnAnimation();
    }

    /**
     * 设置缩略图
     *
     * @param id 图片Id
     */
    public void setThumb(int id) {
        thumbSrc = id;
        updateThumb();
        updateCircleRectF(w,h);
        culDrawProgressParamsAgain();
        postInvalidateOnAnimation();
    }

    /**
     * 设置缩略图大小
     *
     * @param width 宽度,单位dp
     */
    public void setThumbWidth(int width) {
        thumbWidth = width;
        updateThumb();
        updateCircleRectF(w,h);
        culDrawProgressParamsAgain();
        postInvalidateOnAnimation();
    }
}

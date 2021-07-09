package com.zj.tools.zjutils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import com.zj.tools.mylibrary.view.CircleProgressBar;

public class CirProgressBarActivity extends BaseActivity implements View.OnClickListener {

    private SeekBar seekMax;
    private SeekBar seekProgress;
    private CircleProgressBar circleProgress;
    private ImageView ivBgColor;
    private SeekBar seekBgColor;
    private ImageView ivBgColorStart;
    private SeekBar seekBgColorStart;
    private ImageView ivBgColorMid;
    private SeekBar seekBgColorMid;
    private ImageView ivBgColorEnd;
    private SeekBar seekBgColorEnd;
    private ImageView ivProgressColor;
    private SeekBar seekProgressColor;
    private ImageView ivProgressColorStart;
    private SeekBar seekProgressColorStart;
    private ImageView ivProgressColorMid;
    private SeekBar seekProgressColorMid;
    private ImageView ivProgressColorEnd;
    private SeekBar seekProgressColorEnd;
    private SeekBar seekStartAngle;
    private SeekBar seekCirclePercent;
    private SeekBar seekStrokeWidth;
    private SeekBar seekThumbWidth;
    private Switch seekThumbSwitch;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, CirProgressBarActivity.class));
    }

    @Override
    protected String title() {
        return "CirProgressBar";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cir_progress_bar);
        initView();
    }

    private void initView() {
        circleProgress = findViewById(R.id.circle_progress);
        seekProgress = findViewById(R.id.seek_progress);
        seekProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleProgress.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ivBgColor = findViewById(R.id.iv_bg_color);
        seekBgColor = findViewById(R.id.seek_bg_color);
        seekBgColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int argb = Color.argb(255, 0, progress, 0);
                circleProgress.setBgColor(argb);
                ivBgColor.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivBgColorStart = findViewById(R.id.iv_bg_color_start);
        seekBgColorStart = findViewById(R.id.seek_bg_color_start);
        seekBgColorStart.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    circleProgress.setBgColorStart(0);
                    ivBgColorStart.setBackgroundColor(0);
                    return;
                }
                final int argb = Color.argb(255, 100, progress, 0);
                circleProgress.setBgColorStart(argb);
                ivBgColorStart.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivBgColorMid = findViewById(R.id.iv_bg_color_mid);
        seekBgColorMid = findViewById(R.id.seek_bg_color_mid);
        seekBgColorMid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    circleProgress.setBgColorMid(0);
                    ivBgColorMid.setBackgroundColor(0);
                    return;
                }
                final int argb = Color.argb(255, 200, progress, 0);
                circleProgress.setBgColorMid(argb);
                ivBgColorMid.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivBgColorEnd = findViewById(R.id.iv_bg_color_end);
        seekBgColorEnd = findViewById(R.id.seek_bg_color_end);
        seekBgColorEnd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    circleProgress.setBgColorEnd(0);
                    ivBgColorEnd.setBackgroundColor(0);
                    return;
                }
                final int argb = Color.argb(255, 250, progress, 0);
                circleProgress.setBgColorEnd(argb);
                ivBgColorEnd.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivProgressColor = findViewById(R.id.iv_progress_color);
        ivProgressColor.setBackgroundColor(Color.argb(255, 0, 0, 255));
        seekProgressColor = findViewById(R.id.seek_progress_color);
        seekProgressColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int argb = Color.argb(255, 0, 0, 255 - progress);
                circleProgress.setProgressColor(argb);
                ivProgressColor.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ivProgressColorStart = findViewById(R.id.iv_progress_color_start);
        seekProgressColorStart = findViewById(R.id.seek_progress_color_start);
        seekProgressColorStart.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    circleProgress.setProgressColorStart(0);
                    ivProgressColorStart.setBackgroundColor(0);
                    return;
                }
                final int argb = Color.argb(255, 0, progress, 100);
                circleProgress.setProgressColorStart(argb);
                ivProgressColorStart.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivProgressColorMid = findViewById(R.id.iv_progress_color_mid);
        seekProgressColorMid = findViewById(R.id.seek_progress_color_mid);
        seekProgressColorMid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    circleProgress.setProgressColorMid(0);
                    ivProgressColorMid.setBackgroundColor(0);
                    return;
                }
                final int argb = Color.argb(255, 0, progress, 200);
                circleProgress.setProgressColorMid(argb);
                ivProgressColorMid.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivProgressColorEnd = findViewById(R.id.iv_progress_color_end);
        seekProgressColorEnd = findViewById(R.id.seek_progress_color_end);
        seekProgressColorEnd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    circleProgress.setProgressColorEnd(0);
                    ivProgressColorEnd.setBackgroundColor(0);
                    return;
                }
                final int argb = Color.argb(255, 0, progress, 250);
                circleProgress.setProgressColorEnd(argb);
                ivProgressColorEnd.setBackgroundColor(argb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekStartAngle = findViewById(R.id.seek_start_angle);
        seekStartAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleProgress.setStartAngle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekCirclePercent = findViewById(R.id.seek_circle_percent);
        seekCirclePercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleProgress.setCirclePercent(progress * 1.0f / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekStrokeWidth = findViewById(R.id.seek_stroke_width);
        seekStrokeWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleProgress.setStrokeWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekThumbWidth = findViewById(R.id.seek_thumb_width);
        seekThumbWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleProgress.setThumbWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekThumbSwitch = findViewById(R.id.seek_thumb_switch);
        seekThumbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                circleProgress.setThumb(isChecked ? R.drawable.circle_progress_thumb : 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
    }
}
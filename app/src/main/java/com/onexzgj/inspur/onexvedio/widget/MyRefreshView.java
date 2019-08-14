package com.onexzgj.inspur.onexvedio.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onexzgj.inspur.onexvedio.R;
import com.scu.miomin.shswiperefresh.drawable.SHCircleProgressBar;
import com.scu.miomin.shswiperefresh.util.DipUtils;


public class MyRefreshView extends LinearLayout {
    private static final int DEFAULT_CIRCLE_SIZE = 36;
    private SHCircleProgressBar circleProgressBar;
    private AnimationDrawable animationDrawable;
    private TextView tvLoad;
    private ImageView jdImageView;

    public MyRefreshView(Context context) {
        super(context);
        this.setupViews();
    }

    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setupViews();
    }

    public MyRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setupViews();
    }

    private void setupViews() {
        this.setOrientation(HORIZONTAL);
        this.setGravity(17);
        this.circleProgressBar = new SHCircleProgressBar(this.getContext());
        this.jdImageView=new ImageView(this.getContext());

        this.jdImageView.setBackground(getResources().getDrawable(R.drawable.animation_list_refresh_jd));

        this.animationDrawable= (AnimationDrawable) jdImageView.getBackground();
        this.addView(this.jdImageView);
        LinearLayout.LayoutParams lp = new LayoutParams((int) DipUtils.dipToPx(this.getContext(), 36.0F), (int)DipUtils.dipToPx(this.getContext(), 36.0F));
        lp.rightMargin = (int)DipUtils.dipToPx(this.getContext(), 10.0F);
        this.addView(this.circleProgressBar, lp);
        this.tvLoad = new TextView(this.getContext());
        this.addView(this.tvLoad);
    }

    public void setGuidanceView(View view) {
        if (view != null) {
            this.removeAllViews();
            LayoutParams lp = new LayoutParams(-1, -1);
            this.addView(view, lp);
        }
    }

    public void setGuidanceView(@LayoutRes int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View view = inflater.inflate(layoutResID, (ViewGroup)null);
        if (view != null) {
            this.removeAllViews();
            LayoutParams lp = new LayoutParams(-1, -1);
            this.addView(view, lp);
        }
    }

    public void setText(String loadtText) {
        if (this.tvLoad != null) {
            this.tvLoad.setText(loadtText);
        }

    }

    public void setTextColor(int color) {
        if (this.tvLoad != null) {
            this.tvLoad.setTextColor(color);
        }

    }

    public void setProgressBgColor(int color) {
        if (this.circleProgressBar != null) {
            this.circleProgressBar.setBackgroundColor(color);
        }

    }

    public void setProgressColor(int color) {
        if (this.circleProgressBar != null) {
            this.circleProgressBar.setColorSchemeColors(new int[]{color});
        }

    }

    public void startAnimation() {
        if (this.circleProgressBar != null) {
            this.circleProgressBar.start();
            this.animationDrawable.start();

        }

    }

    public void setStartEndTrim(float startAngle, float endAngle) {
        if (this.circleProgressBar != null) {
            this.circleProgressBar.setStartEndTrim(startAngle, endAngle);
        }

    }

    public void stopAnimation() {
        if (this.circleProgressBar != null) {
            this.circleProgressBar.stop();
            this.animationDrawable.stop();
        }

    }

    public void setProgressRotation(float rotation) {
        if (this.circleProgressBar != null) {
            this.circleProgressBar.setProgressRotation(rotation);
        }

    }
}

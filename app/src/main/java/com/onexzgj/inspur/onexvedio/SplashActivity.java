package com.onexzgj.inspur.onexvedio;

import android.graphics.Typeface;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.onexzgj.inspur.onexvedio.ui.activity.MainActivity;
import com.onexzgj.onexlibrary.base.BaseActivity;

import butterknife.BindView;

/**
 * des：闪屏页面
 * author：onexzgj
 * time：2019/7/15
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_as_splash)
    ImageView ivAsSplash;
    @BindView(R.id.tv_as_info)
    TextView tvAsInfo;

    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        Typeface font = Typeface.createFromAsset(this.getAssets(), "font/Lobster-1.4.otf");
        tvAsInfo.setTypeface(font);

    }

    @Override
    protected void initData() {




        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation =new ScaleAnimation(1.0f,1.2f,1.0f,1.2f,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        AnimationSet animationSet =new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(1500);
        ivAsSplash.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }

}

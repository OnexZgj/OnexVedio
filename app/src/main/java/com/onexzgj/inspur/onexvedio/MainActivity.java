package com.onexzgj.inspur.onexvedio;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.onexzgj.inspur.onexvedio.ui.attention.AttentionFragment;
import com.onexzgj.inspur.onexvedio.ui.find.FindFragment;
import com.onexzgj.inspur.onexvedio.ui.home.HomeFragment;
import com.onexzgj.inspur.onexvedio.ui.me.MeFragment;
import com.onexzgj.onexlibrary.base.BaseActivity;
import com.onexzgj.onexlibrary.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    private ArrayList<BaseFragment> mFragments;


    /**
     * 上一次的Fragment实例的索引
     */
    private int mLastFgIndex;

    @Override
    protected void initView() {
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.getInstance());
        mFragments.add(AttentionFragment.getInstance());
        mFragments.add(FindFragment.getInstance());
        mFragments.add(MeFragment.getInstance());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                //首页
                switchFragment(0);
                break;
            case R.id.navigation_attention:
                switchFragment(1);

                break;
            case R.id.navigation_me:
                switchFragment(2);

                break;
            case R.id.navigation_find:
                switchFragment(3);
                break;

        }

        return true;
    }


    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment targetFg = mFragments.get(position);

        Slide slideTransition;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Gravity.START部分机型崩溃java.lang.IllegalArgumentException: Invalid slide direction
            slideTransition = new Slide(Gravity.LEFT);
            slideTransition.setDuration(700);
            targetFg.setEnterTransition(slideTransition);
            targetFg.setExitTransition(slideTransition);
        }


        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded())
            ft.add(R.id.layout_fragment, targetFg);

        ft.show(targetFg);

        ft.commitAllowingStateLoss();
    }


    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}

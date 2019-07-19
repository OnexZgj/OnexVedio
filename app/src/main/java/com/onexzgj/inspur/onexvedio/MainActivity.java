package com.onexzgj.inspur.onexvedio;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
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


    private String permissionInfo;

    private final int SDK_PERMISSION_REQUEST = 127;

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


        getPersimmions();

        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.getInstance());
        mFragments.add(AttentionFragment.getInstance());
        mFragments.add(FindFragment.getInstance());
        mFragments.add(MeFragment.getInstance());
        switchFragment(0);
    }



    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */

            permissions.add(Manifest.permission.INTERNET);
            permissions.add(Manifest.permission.ACCESS_NETWORK_STATE);

            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
             */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }


    //    @RequiresApi(api = Build.VERSION_CODES.M)
    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }

        }else{
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                switchFragment(0);
                setToolbarTitle("首页");
                break;
            case R.id.navigation_attention:
                switchFragment(1);
                setToolbarTitle("关注");
                break;
            case R.id.navigation_find:
                switchFragment(2);
                setToolbarTitle("发现");
                break;
            case R.id.navigation_me:
                switchFragment(3);
                setToolbarTitle("我的");
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
            slideTransition.setDuration(500);
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

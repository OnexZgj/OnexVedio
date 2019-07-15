package com.onexzgj.onexlibrary.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onexzgj.onexlibrary.constant.Constant;
import com.onexzgj.onexlibrary.constant.LoadType;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 全局application
     */
    public Application mApplication;

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bind = ButterKnife.bind(this);
        }
//        BarUtils.setStatusBarVisibility(this, true);
        //初始化
        initView();
        initData();

    }

    protected abstract int getLayoutId();


    protected abstract void initView();


    protected abstract void initData();


    /**
     * 跳转指定的activity
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }


    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    protected void onDestroy() {
        if (bind != null)
            bind.unbind();
        super.onDestroy();
    }
}

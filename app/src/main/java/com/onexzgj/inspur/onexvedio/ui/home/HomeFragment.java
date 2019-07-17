package com.onexzgj.inspur.onexvedio.ui.home;

import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

public class HomeFragment  extends BaseFragment {


    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}

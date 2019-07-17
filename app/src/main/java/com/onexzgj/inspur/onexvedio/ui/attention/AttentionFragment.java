package com.onexzgj.inspur.onexvedio.ui.attention;

import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

public class AttentionFragment extends BaseFragment {

    public static AttentionFragment getInstance(){
        return new AttentionFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}

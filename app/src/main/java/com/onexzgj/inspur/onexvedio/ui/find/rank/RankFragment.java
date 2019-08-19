package com.onexzgj.inspur.onexvedio.ui.find.rank;

import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

/**
 * 排行Fragment
 */
public class RankFragment extends BaseFragment {

    public static RankFragment getInstance(){
        return new RankFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}

package com.onexzgj.inspur.onexvedio.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomePresnter> implements HomeContract.View{


    @BindView(R.id.rv_fh_home)
    RecyclerView rvFhHome;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mPresenter.loadHomeData(1);

    }

    @Override
    protected HomePresnter initPresenter() {
        return new HomePresnter(this);
    }


    @Override
    public void showHomeData(HomeBean homeBean) {

    }
}

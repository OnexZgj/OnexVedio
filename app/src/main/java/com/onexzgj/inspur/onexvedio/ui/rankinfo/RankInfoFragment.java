package com.onexzgj.inspur.onexvedio.ui.rankinfo;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.RankBean;
import com.onexzgj.inspur.onexvedio.widget.SHSwipeRefreshLayout;
import com.onexzgj.onexlibrary.base.BaseFragment;

import butterknife.BindView;

public class RankInfoFragment extends BaseFragment<RankInfoPresenter> implements RankInfoContract.View, SHSwipeRefreshLayout.SHSOnRefreshListener {


    private String mTag = "";
    @BindView(R.id.rv_fri_rank)
    RecyclerView rvFriRank;
    @BindView(R.id.ssrl_fri_refresh)
    SHSwipeRefreshLayout ssrlFriRefresh;
    private RankInfoAdapter mAdapter;

    public RankInfoFragment() {

    }

    @SuppressLint("ValidFragment")
    public RankInfoFragment(String tag) {
        mTag = tag;
    }

//    public static BaseFragment getInstance() {
//        mTag = tag;
//        return new RankInfoFragment();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank_info;
    }

    @Override
    protected void initView(View view) {


        ssrlFriRefresh.setOnRefreshListener(this);

        ssrlFriRefresh.setLoadmoreEnable(false);

        mAdapter = new RankInfoAdapter(getActivity());

        rvFriRank.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFriRank.setAdapter(mAdapter);

        mPresenter.loadRankData(mTag);
    }

    @Override
    protected RankInfoPresenter initPresenter() {
        return new RankInfoPresenter(this);
    }


    @Override
    public void showRankData(RankBean rankBean) {
        ssrlFriRefresh.finishRefresh();
        mAdapter.setNewData(rankBean.getItemList());
    }

    @Override
    public void onRefresh() {
        mPresenter.loadRankData(mTag);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onRefreshPulStateChange(float percent, int state) {

    }

    @Override
    public void onLoadmorePullStateChange(float percent, int state) {

    }
}

package com.onexzgj.inspur.onexvedio.ui.find.follow;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.FollowBeans;
import com.onexzgj.inspur.onexvedio.widget.SHSwipeRefreshLayout;
import com.onexzgj.onexlibrary.base.BaseFragment;

import butterknife.BindView;

/**
 * 关注Fragment
 */
public class FollowFragment extends BaseFragment<FollowPresenter> implements FollowContract.View {

    @BindView(R.id.rv_ff_follow)
    RecyclerView rvFfFollow;
    @BindView(R.id.ssrl_refresh)
    SHSwipeRefreshLayout ssrlRefresh;
    private FollowAdapter mFollowAdapter;

    public static FollowFragment getInstance() {
        return new FollowFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    protected void initView(View view) {
        mPresenter.loadFollowData();
        mFollowAdapter = new FollowAdapter();
        rvFfFollow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFfFollow.setAdapter(mFollowAdapter);

    }

    @Override
    protected FollowPresenter initPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    public void showFollowData(FollowBeans followBeans) {
        mFollowAdapter.setNewData(followBeans.getItemList());
    }


}

package com.onexzgj.inspur.onexvedio.ui.activity.categorydetail;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.CategoryInfo;
import com.onexzgj.inspur.onexvedio.constant.Constant;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseMvpActivity;

import butterknife.BindView;

public class CategoryDetailActivity extends BaseMvpActivity<CategoryActivityPresenter> implements CategoryActivityContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.iv_acd_head)
    ImageView ivAcdHead;
    @BindView(R.id.rv_acd_category)
    RecyclerView rvAcdCategory;
    @BindView(R.id.srl_acd_refresh)
    SwipeRefreshLayout srlAcdRefresh;
    @BindView(R.id.head_layout)
    LinearLayout headLayout;
    @BindView(R.id.tb_atf_toolbar)
    Toolbar tbAtfToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.coordinator_Layout)
    CoordinatorLayout coordinatorLayout;
    /**
     * 广告分类ID
     */
    private int mId = 0;
    /**
     * 头背景图片
     */
    private String mBgUrl = "";
    private CategoryActivityAdapter mCategoryAdapter;
    private String mName = "";

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return new CategoryActivityPresenter(this);
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            mId = getIntent().getIntExtra(Constant.CATEGORY_ID, 0);
            mBgUrl = getIntent().getStringExtra(Constant.CATEGORY_BG_ID);
            mName = getIntent().getStringExtra(Constant.CATEGORY_NAME);
        }

        if (!TextUtils.isEmpty(mBgUrl))
            Glide.with(this).load(mBgUrl).into(ivAcdHead);

        tbAtfToolbar.setTitle(mName);

        mCategoryAdapter = new CategoryActivityAdapter();
        rvAcdCategory.setLayoutManager(new LinearLayoutManager(this));
        rvAcdCategory.setAdapter(mCategoryAdapter);

        srlAcdRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.loadCategoryData(mId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_detail;
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void showCategoryInfo(CategoryInfo categoryInfo, int loadType) {
        if (srlAcdRefresh.isRefreshing())
            srlAcdRefresh.setRefreshing(false);
        setLoadDataResult(mCategoryAdapter, srlAcdRefresh, categoryInfo.getItemList(), loadType);
    }


    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }
}

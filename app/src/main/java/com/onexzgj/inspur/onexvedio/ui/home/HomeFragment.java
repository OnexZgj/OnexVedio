package com.onexzgj.inspur.onexvedio.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.constant.Constant;
import com.onexzgj.inspur.onexvedio.utils.GlideImageLoader;
import com.onexzgj.onexlibrary.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresnter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.rv_fh_home)
    RecyclerView rvFhHome;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mSrlRefresh;
    private List<HomeBean.IssueListBean.ItemListBean> mDatas = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    private Banner banner;

    /**
     * Banner图片合集
     */
    private List<String> images = new ArrayList<>();

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        mHomeAdapter = new HomeAdapter(mDatas);
        rvFhHome.setLayoutManager(new LinearLayoutManager(getActivity()));


        banner = new Banner(getActivity());
        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        rvFhHome.setAdapter(mHomeAdapter);
        //设置Banner
        mHomeAdapter.addHeaderView(banner);
        mHomeAdapter.setOnLoadMoreListener(this,rvFhHome);

        mPresenter.loadHomeData(1);
        mSrlRefresh.setOnRefreshListener(this);


        mHomeAdapter.setOnItemChildClickListener(this);


    }

    @Override
    protected HomePresnter initPresenter() {
        return new HomePresnter(this);
    }


    @Override
    public void showBannerData(HomeBean homeBean) {

        if (mSrlRefresh.isRefreshing()) {
            mSrlRefresh.setRefreshing(false);
            hideLoading();
        }

        for (HomeBean.IssueListBean.ItemListBean item : mDatas) {
            if (item.getType().equals("video")) {
                if (item.getData().getCover().getDetail() != null)
                    images.add(item.getData().getCover().getDetail());
            }
        }

        if (images != null && images.size() > 0) {
            //设置图片集合
            banner.setImages(images);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }

    @Override
    public void showHomeData(HomeBean homeBean, int loadType) {

        List<HomeBean.IssueListBean.ItemListBean> itemList=new ArrayList<>();
        if (homeBean!=null){
            itemList = homeBean.getIssueList().get(0).getItemList();
            itemList.remove(0);
        }

        setLoadDataResult(mHomeAdapter,mSrlRefresh,itemList,loadType);

    }


    @Override
    public void onRefresh() {
        if (mDatas != null && mDatas.size() > 0)
            mDatas.clear();
        mPresenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.iv_ihv_cover_feed:
                showToast("点击了...");
                break;
        }
    }
}

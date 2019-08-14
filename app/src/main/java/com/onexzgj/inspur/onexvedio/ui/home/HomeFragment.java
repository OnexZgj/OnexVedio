package com.onexzgj.inspur.onexvedio.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.constant.Constant;
import com.onexzgj.inspur.onexvedio.ui.activity.detail.VedioActivity;
import com.onexzgj.inspur.onexvedio.utils.GlideImageLoader;
import com.onexzgj.onexlibrary.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresnter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.rv_fh_home)
    RecyclerView rvFhHome;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mSrlRefresh;
    private List<HomeBean.IssueListBean.ItemListBean> mDatas ;
    private HomeAdapter mHomeAdapter;
    private Banner banner;



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

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        params.setMargins(10,10,10,40);


        banner.setLayoutParams(params);


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        banner.setBannerAnimation(Transformer.ZoomOutSlide);

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

        ArrayList<String> titles =new ArrayList<>();
        /**
         * Banner图片合集
         */
         List<String> images = new ArrayList<>();

        for (HomeBean.IssueListBean.ItemListBean item : mDatas) {
            if (item.getType().equals("video")) {
                if (item.getData().getCover().getDetail() != null && item.getData().getTitle()!=null)
                    titles.add(item.getData().getTitle());
                    images.add(item.getData().getCover().getDetail());
            }
        }



        if (images != null && images.size() > 0) {

            banner.setBannerTitles(titles);
            //设置图片集合
            banner.setImages(images);

            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }

    @Override
    public void showHomeData(HomeBean homeBean, int loadType) {

        mDatas = new ArrayList<>();
        if (homeBean!=null){
            mDatas = homeBean.getIssueList().get(0).getItemList();
            mDatas.remove(0);
        }

        setLoadDataResult(mHomeAdapter,mSrlRefresh,mDatas,loadType);

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
                showToast("点击了..." + position);

                showToast(((List<HomeBean.IssueListBean.ItemListBean>)adapter.getData()).get(position).getData().getPlayInfo().get(0).getUrl());

                HomeBean.IssueListBean.ItemListBean.DataBean data=  ((List<HomeBean.IssueListBean.ItemListBean>)adapter.getData()).get(position).getData();

                Intent intent=new Intent(getActivity(), VedioActivity.class);
                intent.putExtra(Constant.PLAY_VEDIO_URL,data);
                startActivity(intent);
                break;
        }
    }
}

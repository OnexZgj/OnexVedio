package com.onexzgj.inspur.onexvedio.ui.find;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.utils.GlideImageLoader;
import com.onexzgj.inspur.onexvedio.widget.BGAMeiTuanRefreshViewHolder;
import com.onexzgj.inspur.onexvedio.widget.OnRefreshListener;
import com.onexzgj.inspur.onexvedio.widget.SwipeToLoadLayout;
import com.onexzgj.onexlibrary.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

public class FindFragment extends BaseFragment<HomePresnter> implements HomeContract.View, OnRefreshListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.rv_fh_find)
    RecyclerView rvFhHome;
    @BindView(R.id.rl_ff_refresh)
    BGARefreshLayout mRefreshLayout;
    private List<HomeBean.IssueListBean.ItemListBean> mDatas = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    private Banner banner;

    /**
     * Banner图片合集
     */
    private List<String> images = new ArrayList<>();

    public static FindFragment getInstance() {
        return new FindFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {

        mHomeAdapter = new HomeAdapter(mDatas);
        rvFhHome.setLayoutManager(new LinearLayoutManager(getActivity()));


        banner= new Banner(getActivity());
        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500 ));


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        rvFhHome.setAdapter(mHomeAdapter);
        //设置Banner
        mHomeAdapter.addHeaderView(banner);

        mPresenter.loadHomeData(1);
//        swipeToLoadLayout.setOnRefreshListener(this);


        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGAMeiTuanRefreshViewHolder(getActivity(), false);
        // 设置下拉刷新和上拉加载更多的风格
        ((BGAMeiTuanRefreshViewHolder) refreshViewHolder).setPullDownImageResource(R.drawable.bga_refresh_mt_refreshing);
        ((BGAMeiTuanRefreshViewHolder) refreshViewHolder).setRefreshingAnimResId(R.drawable.animation_list_refresh_jd);
        ((BGAMeiTuanRefreshViewHolder) refreshViewHolder).setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);


        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
         mRefreshLayout.setIsShowLoadingMoreView(false);
        // 设置正在加载更多时的文本
//        refreshViewHolder.setLoadingMoreText(loadingMoreText);
        // 设置整个加载更多控件的背景颜色资源 id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes);
        // 设置整个加载更多控件的背景 drawable 资源 id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
        // 设置下拉刷新控件的背景颜色资源 id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
        // 设置下拉刷新控件的背景 drawable 资源 id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//        mRefreshLayout.setCustomHeaderView(banner, false);



    }

    @Override
    protected HomePresnter initPresenter() {
        return new HomePresnter(this);
    }


    @Override
    public void showHomeData(HomeBean homeBean) {
        mRefreshLayout.endRefreshing();

//        if (swipeToLoadLayout.isRefreshing()){
//            swipeToLoadLayout.setRefreshing(false);
//        }


        mDatas.addAll(homeBean.getIssueList().get(0).getItemList());
        mHomeAdapter.notifyDataSetChanged();




        for (HomeBean.IssueListBean.ItemListBean item : mDatas) {
            if (item.getType().equals("video")) {
                if (item.getData().getCover().getDetail()!=null)
                images.add(item.getData().getCover().getDetail());
            }
        }



        if (images!=null && images.size()>0) {
            //设置图片集合
            banner.setImages(images);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }

    }

    @Override
    public void onRefresh() {
//        swipeToLoadLayout.setRefreshing(true);
        mPresenter.refresh();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.refresh();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}

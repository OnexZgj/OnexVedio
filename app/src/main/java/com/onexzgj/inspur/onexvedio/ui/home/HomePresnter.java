package com.onexzgj.inspur.onexvedio.ui.home;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.constant.LoadType;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;
import com.orhanobut.logger.Logger;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresnter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{


    private int mCid = 0;

    public HomePresnter(HomeContract.View view) {
        this.mView=view;
    }

    private HomeBean mFirstBannerData =new HomeBean();
    private String mNextPageUrl="";

    @SuppressLint("CheckResult")
    @Override
    public void loadHomeData(int cid) {
        this.mCid=cid;

        mView.showLoading("正在加载中...");
        RetrofitHelper.createApi(ApiService.class).getHomeData(1)

                .subscribeOn(Schedulers.io())
                .compose(mView.<HomeBean>bindToLife())
                .flatMap(new Function<HomeBean, ObservableSource<HomeBean>>() {
                    @Override
                    public ObservableSource<HomeBean> apply(HomeBean homeBean) throws Exception {
                        if (homeBean!=null){
                            mFirstBannerData=homeBean;
                            return RetrofitHelper.createApi(ApiService.class).getMoreHomeData(homeBean.getNextPageUrl());
                        }
                        return null;
                    }
                })
                .compose(RxHelper.<HomeBean>rxSchedulerHelper())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        if (homeBean!=null) {
                            mView.showHomeData(homeBean, LoadType.TYPE_REFRESH_SUCCESS);
                            mView.showBannerData(mFirstBannerData);
                            mNextPageUrl = homeBean.getNextPageUrl();
                        }else{
                            mView.showToast("没有请求到数据....");
                        }
                        mView.hideLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showToast("请求出问题咯...");
                        mView.hideLoading();
                        Logger.d(throwable.getMessage());

                    }
                });
    }

    @Override
    public void refresh() {
        loadHomeData(mCid);
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadMore() {
        mView.showLoading("正在加载中...");
        if (!TextUtils.isEmpty(mNextPageUrl)) {
            RetrofitHelper.createApi(ApiService.class).getMoreHomeData(mNextPageUrl)
                    .compose(mView.<HomeBean>bindToLife())
                    .compose(RxHelper.<HomeBean>rxSchedulerHelper())
                    .subscribe(new Consumer<HomeBean>() {
                        @Override
                        public void accept(HomeBean homeBean) throws Exception {
                            mView.showHomeData(homeBean,LoadType.TYPE_LOAD_MORE_SUCCESS);
                            mView.hideLoading();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mView.showToast("请求出问题咯...");
                            Logger.d(throwable.getMessage());
                            mView.hideLoading();
                        }
                    });
        }
    }
}

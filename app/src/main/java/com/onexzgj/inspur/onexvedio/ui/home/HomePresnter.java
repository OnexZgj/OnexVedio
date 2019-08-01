package com.onexzgj.inspur.onexvedio.ui.home;

import android.text.TextUtils;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;
import com.orhanobut.logger.Logger;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class HomePresnter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{


    public HomePresnter(HomeContract.View view) {
        this.mView=view;
    }

    private HomeBean mFirstBannerData =new HomeBean();
    private String mNextPageUrl="";

    @Override
    public void loadHomeData(int cid) {

        mView.showLoading("正在加载中...");
        RetrofitHelper.createApi(ApiService.class).getHomeData(1)
                .compose(RxHelper.<HomeBean>rxSchedulerHelper())
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
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        mView.showHomeData(homeBean);
                        mView.showBannerData(mFirstBannerData);
                        mView.showToast(homeBean.getNextPageUrl());
                        mNextPageUrl=homeBean.getNextPageUrl();
                        mView.hideLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showToast("请求出问题咯...");
                        Logger.d(throwable.getMessage().toString());
                        mView.hideLoading();
                    }
                });




    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {
        mView.showLoading("正在加载中...");
        if (TextUtils.isEmpty(mNextPageUrl)) {
            RetrofitHelper.createApi(ApiService.class).getMoreHomeData(mNextPageUrl)
                    .compose(mView.<HomeBean>bindToLife())
                    .compose(RxHelper.<HomeBean>rxSchedulerHelper())
                    .subscribe(new Consumer<HomeBean>() {
                        @Override
                        public void accept(HomeBean homeBean) throws Exception {
                            mView.showHomeData(homeBean);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mView.showToast("请求出问题咯...");
                            Logger.d(throwable.getMessage().toString());
                            mView.hideLoading();
                        }
                    });
        }
    }
}

package com.onexzgj.inspur.onexvedio.ui.find;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;
import com.orhanobut.logger.Logger;

import io.reactivex.functions.Consumer;

public class FindPresnter extends BasePresenter<FindContract.View> implements FindContract.Presenter{

    private int mCid=0;

    public FindPresnter(FindContract.View view) {
        this.mView=view;
    }

    @Override
    public void loadHomeData(int cid) {
        mView.showLoading("正在加载中...");
        this.mCid=cid;
        RetrofitHelper.createApi(ApiService.class).getHomeData(1)
                .compose(RxHelper.<HomeBean>rxSchedulerHelper())
                .compose(mView.<HomeBean>bindToLife())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        Logger.d("成功了");
                        mView.showHomeData(homeBean);
                        mView.showToast(homeBean.getNextPageUrl());
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showToast("网络异常了...");
                        Logger.d(throwable.getMessage().toString());
                        mView.hideLoading();
                    }
                });

    }

    @Override
    public void refresh() {
        loadHomeData(mCid);
    }

    @Override
    public void loadMore() {

    }
}

package com.onexzgj.inspur.onexvedio.ui.find.follow;

import android.annotation.SuppressLint;

import com.onexzgj.inspur.onexvedio.bean.FollowBeans;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;

import io.reactivex.functions.Consumer;

public class FollowPresenter extends BasePresenter<FollowContract.View> implements FollowContract.Presenter {

    public FollowPresenter(FollowContract.View view) {
        this.mView = view;
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadFollowData() {
        mView.showLoading("");
        RetrofitHelper.createApi(ApiService.class).getFollow()
                .compose(mView.bindToLife())
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(new Consumer<FollowBeans>() {
                    @Override
                    public void accept(FollowBeans followBeans) throws Exception {
                        mView.showFollowData(followBeans);
                        mView.hideLoading();
                    }
                }, throwable -> {
                    mView.showToast("加载信息出错...");
                    mView.hideLoading();
                });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }
}

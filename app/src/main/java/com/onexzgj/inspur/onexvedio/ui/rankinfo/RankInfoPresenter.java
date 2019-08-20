package com.onexzgj.inspur.onexvedio.ui.rankinfo;

import android.annotation.SuppressLint;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.bean.RankBean;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;
import com.orhanobut.logger.Logger;

import io.reactivex.functions.Consumer;

public class RankInfoPresenter extends BasePresenter<RankInfoContract.View> implements RankInfoContract.Presenter {


    public RankInfoPresenter(RankInfoContract.View view) {
        this.mView=view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadRankData(String tag) {
        mView.showLoading("");
        RetrofitHelper.createApi(ApiService.class).getRankInfo(tag)
                .compose(RxHelper.rxSchedulerHelper())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<RankBean>() {
                    @Override
                    public void accept(RankBean rankBean) throws Exception {
                        mView.showRankData(rankBean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.d(throwable.getMessage());
                        mView.showToast("服务器出错了...");
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }


}

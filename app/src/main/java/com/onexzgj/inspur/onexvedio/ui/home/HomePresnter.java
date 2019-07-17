package com.onexzgj.inspur.onexvedio.ui.home;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;

import io.reactivex.functions.Consumer;

public class HomePresnter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{


    public HomePresnter(HomeContract.View view) {
        this.mView=view;
    }

    @Override
    public void loadHomeData(int cid) {
        mView.showLoading("正在加载中...");
        mView.showToast("ssss");
        RetrofitHelper.createApi(ApiService.class).getHomeData()
                .compose(RxHelper.<HomeBean>rxSchedulerHelper())
                .compose(mView.<HomeBean>bindToLife())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {

                        mView.showHomeData(homeBean);
                        mView.showToast("success");
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showToast("网络异常了...");
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

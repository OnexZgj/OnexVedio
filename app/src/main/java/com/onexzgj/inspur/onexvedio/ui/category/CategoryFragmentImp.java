package com.onexzgj.inspur.onexvedio.ui.category;

import android.annotation.SuppressLint;

import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;


public class CategoryFragmentImp extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {


    public CategoryFragmentImp(CategoryContract.View view) {
        this.mView = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        mView.showLoading("");
        RetrofitHelper.createApi(ApiService.class).getCategories()
                .compose(mView.bindToLife())
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(datas -> {
                    mView.showCategory(datas);
                    mView.hideLoading();
                }, throwable -> {
                    mView.showToast("出错了...");
                    mView.hideLoading();
                });
    }

    @Override
    public void refresh() {

    }
}

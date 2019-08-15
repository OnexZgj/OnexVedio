package com.onexzgj.inspur.onexvedio.ui.activity.categorydetail;

import android.annotation.SuppressLint;

import com.onexzgj.inspur.onexvedio.bean.CategoryInfo;
import com.onexzgj.inspur.onexvedio.bean.Related;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.constant.LoadType;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;
import com.orhanobut.logger.Logger;

public class CategoryActivityPresenter extends BasePresenter<CategoryActivityContract.View> implements CategoryActivityContract.Presenter {


    public CategoryActivityPresenter(CategoryActivityContract.View view) {
        this.mView = view;
    }

    private long anInt = 0;

    @SuppressLint("CheckResult")
    @Override
    public void loadCategoryData(long id) {

        anInt =id;
        RetrofitHelper.createApi(ApiService.class).getCategoriesInfo(id,"26868b32e808498db32fd51fb422d00175e179df")
                .compose(mView.<CategoryInfo>bindToLife())
                .compose(RxHelper.<CategoryInfo>rxSchedulerHelper())
                .subscribe(categoryInfo -> {
                    mView.showCategoryInfo(categoryInfo, LoadType.TYPE_REFRESH_SUCCESS);
                    mView.hideLoading();
                }, throwable -> {
                    Logger.e(throwable.getMessage());
                    mView.hideLoading();
                    mView.showToast("服务器出错了....");
                });


    }

    @Override
    public void refresh() {

    }
}

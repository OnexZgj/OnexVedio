package com.onexzgj.inspur.onexvedio.ui.activity.detail;

import android.annotation.SuppressLint;

import com.onexzgj.inspur.onexvedio.bean.Related;
import com.onexzgj.inspur.onexvedio.net.ApiService;
import com.onexzgj.onexlibrary.base.BasePresenter;
import com.onexzgj.onexlibrary.constant.LoadType;
import com.onexzgj.onexlibrary.net.RetrofitHelper;
import com.onexzgj.onexlibrary.utils.RxHelper;
import com.orhanobut.logger.Logger;

import io.reactivex.functions.Consumer;

public class VedioActivityPresenter extends BasePresenter<VedioActivityContract.View> implements VedioActivityContract.Presenter {


    public VedioActivityPresenter(VedioActivityContract.View view) {
        this.mView = view;
    }

    private long anInt = 0;

    @SuppressLint("CheckResult")
    @Override
    public void loadRelatedData(long id) {
        anInt =id;
        RetrofitHelper.createApi(ApiService.class).getRelated(id)
                .compose(mView.<Related>bindToLife())
                .compose(RxHelper.<Related>rxSchedulerHelper())
                .subscribe(new Consumer<Related>() {
                    @Override
                    public void accept(Related related) throws Exception {
                        mView.showRelated(related, LoadType.TYPE_LOAD_MORE_SUCCESS);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable.getMessage().toString());
                        mView.hideLoading();
                        mView.showToast("服务器出错了....");
                    }
                });


    }

    @Override
    public void refresh() {

    }
}

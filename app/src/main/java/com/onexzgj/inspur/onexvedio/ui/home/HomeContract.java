package com.onexzgj.inspur.onexvedio.ui.home;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;

public class HomeContract {

    interface View extends BaseContract.BaseView{

        void showHomeData(HomeBean homeBean);
    }

    interface Presenter extends BaseContract.BasePresenter<HomeContract.View>{
        void loadHomeData(int cid);

        void refresh();

        void loadMore();
    }
}

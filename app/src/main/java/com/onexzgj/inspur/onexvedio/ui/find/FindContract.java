package com.onexzgj.inspur.onexvedio.ui.find;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;

public class FindContract {

    interface View extends BaseContract.BaseView{

        void showHomeData(HomeBean homeBean);
    }

    interface Presenter extends BaseContract.BasePresenter<FindContract.View>{
        void loadHomeData(int cid);

        void refresh();

        void loadMore();
    }
}

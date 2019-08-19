package com.onexzgj.inspur.onexvedio.ui.find.follow;

import com.onexzgj.inspur.onexvedio.bean.FollowBeans;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;

public class FollowContract {

    interface View extends BaseContract.BaseView{

        void showFollowData(FollowBeans followBeans);

    }

    interface Presenter extends BaseContract.BasePresenter<FollowContract.View>{
        void loadFollowData();

        void refresh();

        void loadMore();
    }
}

package com.onexzgj.inspur.onexvedio.ui.home;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;

public class HomeContract {

    interface View extends BaseContract.BaseView{

        /**
         * 展示轮播图数据
         * @param homeBean
         */
        void showBannerData(HomeBean homeBean);

        /**
         * 展示首页数据问题
         * @param homeBean
         */
        void showHomeData(HomeBean homeBean);

    }

    interface Presenter extends BaseContract.BasePresenter<HomeContract.View>{
        void loadHomeData(int cid);

        void refresh();

        void loadMore();
    }
}

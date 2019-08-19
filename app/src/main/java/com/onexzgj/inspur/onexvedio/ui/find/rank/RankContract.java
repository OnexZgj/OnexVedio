package com.onexzgj.inspur.onexvedio.ui.find.rank;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;

public class RankContract {

    interface View extends BaseContract.BaseView{

        void showHomeData(HomeBean homeBean);
    }

    interface Presenter extends BaseContract.BasePresenter<RankContract.View>{
        void loadHomeData(int cid);

        void refresh();

        void loadMore();
    }
}

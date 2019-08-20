package com.onexzgj.inspur.onexvedio.ui.rankinfo;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.bean.RankBean;
import com.onexzgj.onexlibrary.base.BaseContract;

public class RankInfoContract {

    interface View extends BaseContract.BaseView{

        void showRankData(RankBean rankBean);
    }

    interface Presenter extends BaseContract.BasePresenter<RankInfoContract.View>{
        /**
         * 根据不同的tag请求不同的数据
         * @param tag
         */
        void loadRankData(String tag);

        void refresh();

        void loadMore();
    }
}

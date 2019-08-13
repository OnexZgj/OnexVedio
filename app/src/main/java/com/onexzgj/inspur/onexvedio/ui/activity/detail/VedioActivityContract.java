package com.onexzgj.inspur.onexvedio.ui.activity.detail;

import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.bean.Related;
import com.onexzgj.onexlibrary.base.BaseContract;

public class VedioActivityContract {

    interface View extends BaseContract.BaseView{

        /**
         * 展示相关视频信息
         * @param relatedBean
         */
        void showRelated(Related relatedBean,int loadType);


    }

    interface Presenter extends BaseContract.BasePresenter<VedioActivityContract.View>{

        /**
         * 加载相关视频
         * @param id
         */
        void loadRelatedData(long id);


        /**
         * 刷新数据
         */
        void refresh();
    }
}

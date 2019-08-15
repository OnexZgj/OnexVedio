package com.onexzgj.inspur.onexvedio.ui.activity.categorydetail;

import com.onexzgj.inspur.onexvedio.bean.CategoryInfo;
import com.onexzgj.inspur.onexvedio.bean.Related;
import com.onexzgj.onexlibrary.base.BaseContract;

public class CategoryActivityContract {

    interface View extends BaseContract.BaseView{

        /**
         * 展示相关视频信息
         * @param categoryInfo 分类视频详细表
         */
        void showCategoryInfo(CategoryInfo categoryInfo, int loadType);


    }

    interface Presenter extends BaseContract.BasePresenter<CategoryActivityContract.View>{

        /**
         * 加载相关视频
         * @param id
         */
        void loadCategoryData(long id);


        /**
         * 刷新数据
         */
        void refresh();
    }
}

package com.onexzgj.inspur.onexvedio.ui.category;

import com.onexzgj.inspur.onexvedio.bean.CategoryBean;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.onexlibrary.base.BaseContract;

import java.util.ArrayList;

public class CategoryContract {

    interface View extends BaseContract.BaseView{

        void showCategory(ArrayList<CategoryBean> datas);

    }

    interface Presenter extends BaseContract.BasePresenter<CategoryContract.View>{

        void loadData();

        void refresh();

    }
}

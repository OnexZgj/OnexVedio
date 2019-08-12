package com.onexzgj.inspur.onexvedio.ui.find;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean.IssueListBean;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter< IssueListBean.ItemListBean,BaseViewHolder> {

    public HomeAdapter( @Nullable List<IssueListBean.ItemListBean> data) {
        super(R.layout.item_home_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IssueListBean.ItemListBean item) {
        helper.setText(R.id.tv_title,item.getData().getTitle());
    }




}

package com.onexzgj.inspur.onexvedio.ui.home;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean.IssueListBean;
import com.onexzgj.onexlibrary.GlobalApplication;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter< IssueListBean.ItemListBean,BaseViewHolder> {

    public HomeAdapter( @Nullable List<IssueListBean.ItemListBean> data) {
        super(R.layout.item_home_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IssueListBean.ItemListBean item) {
        helper.setText(R.id.tv_ihv_author_name,item.getData().getAuthor().getName());
        Glide.with(GlobalApplication.getContext()).load(item.getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.iv_ihv_cover_feed));
        Glide.with(GlobalApplication.getContext()).load(item.getData().getAuthor().getIcon()).into((ImageView) helper.getView(R.id.iv_ihv_author_icon));
        helper.setText(R.id.tv_category,item.getData().getCategory());
        helper.setText(R.id.tv_ihv_title,item.getData().getTitle());
        helper.setText(R.id.tv_ihv_title,item.getData().getTitle());
        helper.setText(R.id.tv_ihv_des,item.getData().getDescription());

        helper.addOnClickListener(R.id.iv_ihv_cover_feed);


    }
}

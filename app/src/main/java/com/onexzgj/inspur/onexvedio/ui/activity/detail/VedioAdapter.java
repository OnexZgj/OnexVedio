package com.onexzgj.inspur.onexvedio.ui.activity.detail;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean.IssueListBean;
import com.onexzgj.inspur.onexvedio.bean.Related;
import com.onexzgj.onexlibrary.GlobalApplication;

import java.util.List;

public class VedioAdapter extends BaseQuickAdapter< Related.ItemListBean,BaseViewHolder> {

    public VedioAdapter(@Nullable List< Related.ItemListBean> data) {
        super(R.layout.item_vedio_related, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  Related.ItemListBean item) {
        Glide.with(GlobalApplication.getContext()).load(item.getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.iv_ivr_icon));
        helper.setText(R.id.tv_ivr_title,item.getData().getTitle());
        helper.setText(R.id.tv_ivr_category,item.getData().getCategory());
    }
}

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

public class VedioAdapter extends BaseQuickAdapter<Related.ItemListBean, BaseViewHolder> {

    public VedioAdapter(@Nullable List<Related.ItemListBean> data) {
        super(R.layout.item_vedio_related, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Related.ItemListBean item) {
        if (item.getData().getCover() != null) {
            Glide.with(GlobalApplication.getContext()).load(item.getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.iv_ivr_icon));
        }else {
            Glide.with(GlobalApplication.getContext()).load("http://img.kaiyanapp.com/90cc6d8e620ee01888b6d1de74f2b6a8.jpeg?imageMogr2/quality/60/format/jpg").into((ImageView) helper.getView(R.id.iv_ivr_icon));
        }
        if (item.getData().getTitle() != null) {
            helper.setText(R.id.tv_ivr_title, item.getData().getTitle());
        }else{
            helper.setText(R.id.tv_ivr_title, "疼起来要人命的偏头痛是什么导致的？");
        }
        if (item.getData().getCategory() != null) {
            helper.setText(R.id.tv_ivr_category, item.getData().getCategory());
        }else{
            helper.setText(R.id.tv_ivr_category,"推荐");
        }
        helper.addOnClickListener(R.id.iv_ivr_share);
    }
}

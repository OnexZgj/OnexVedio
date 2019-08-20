package com.onexzgj.inspur.onexvedio.ui.find.follow;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.RankBean;
import com.onexzgj.onexlibrary.GlobalApplication;

import java.util.List;

public class ItemFollowAdapter extends BaseQuickAdapter<RankBean.ItemListBean.DataBean.TagsBean,BaseViewHolder> {


    public ItemFollowAdapter( @Nullable List<RankBean.ItemListBean.DataBean.TagsBean> data) {
        super(R.layout.item_iv_follow, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,RankBean.ItemListBean.DataBean.TagsBean item) {
        Glide.with(GlobalApplication.getContext()).load(item.getHeaderImage()).into((ImageView) helper.getView(R.id.iv_iif_image));
    }
}

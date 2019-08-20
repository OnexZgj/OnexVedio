package com.onexzgj.inspur.onexvedio.ui.rankinfo;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.FollowBeans;
import com.onexzgj.onexlibrary.GlobalApplication;

import java.util.List;

public class RankInfoTagAdapter extends BaseQuickAdapter<FollowBeans.ItemListBeanX.DataBeanX.ItemListBean,BaseViewHolder> {


    public RankInfoTagAdapter(@Nullable List<FollowBeans.ItemListBeanX.DataBeanX.ItemListBean> data) {
        super(R.layout.item_iv_follow, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowBeans.ItemListBeanX.DataBeanX.ItemListBean item) {
        Glide.with(GlobalApplication.getContext()).load(item.getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.iv_iif_image));
    }
}

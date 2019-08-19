package com.onexzgj.inspur.onexvedio.ui.find.follow;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.CategoryBean;
import com.onexzgj.inspur.onexvedio.bean.FollowBeans;
import com.onexzgj.onexlibrary.GlobalApplication;

public class FollowAdapter extends BaseQuickAdapter<FollowBeans.ItemListBeanX,BaseViewHolder> {

    public FollowAdapter() {
        super(R.layout.item_follow, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, FollowBeans.ItemListBeanX item) {

        helper.setText(R.id.tv_if_title,item.getData().getHeader().getTitle());
        helper.setText(R.id.tv_if_desc,item.getData().getHeader().getDescription());
        Glide.with(GlobalApplication.getContext()).load(item.getData().getHeader().getIcon()).into((ImageView) helper.getView(R.id.iv_if_author_head));

        RecyclerView  rvIfRecycleView = helper.getView(R.id.rv_if_recyclerView);

        ItemFollowAdapter itemFollowAdapter = new ItemFollowAdapter(item.getData().getItemList());
        rvIfRecycleView.setLayoutManager(new LinearLayoutManager(GlobalApplication.getContext(),LinearLayoutManager.HORIZONTAL,false));

        rvIfRecycleView.setAdapter(itemFollowAdapter);


    }
}

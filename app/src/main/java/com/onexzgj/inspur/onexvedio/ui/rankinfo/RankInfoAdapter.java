package com.onexzgj.inspur.onexvedio.ui.rankinfo;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.FollowBeans;
import com.onexzgj.inspur.onexvedio.bean.RankBean;
import com.onexzgj.inspur.onexvedio.constant.Constant;
import com.onexzgj.inspur.onexvedio.ui.activity.detail.VedioActivity;
import com.onexzgj.inspur.onexvedio.ui.find.follow.ItemFollowAdapter;
import com.onexzgj.inspur.onexvedio.widget.BannerLayout;
import com.onexzgj.onexlibrary.GlobalApplication;

import java.util.List;

public class RankInfoAdapter extends BaseQuickAdapter<RankBean.ItemListBean,BaseViewHolder> {


   private  FragmentActivity  mActivity;

    public RankInfoAdapter(FragmentActivity activity) {
        super(R.layout.item_follow, null);
        this.mActivity=activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, RankBean.ItemListBean item) {

        helper.setText(R.id.tv_if_title,item.getData().getTitle());
        helper.setText(R.id.tv_if_desc,item.getData().getDescription());
        Glide.with(GlobalApplication.getContext()).load(item.getData().getAuthor().getIcon()).into((ImageView) helper.getView(R.id.iv_if_author_head));

        BannerLayout rvIfRecycleView = helper.getView(R.id.rv_if_recyclerView);


        List<RankBean.ItemListBean.DataBean.TagsBean> tags = item.getData().getTags();

        ItemFollowAdapter itemFollowAdapter = new ItemFollowAdapter(tags);
//        rvIfRecycleView.setLayoutManager(new LinearLayoutManager(GlobalApplication.getContext(),LinearLayoutManager.HORIZONTAL,false));

        rvIfRecycleView.setAdapter(itemFollowAdapter);

        itemFollowAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mActivity, VedioActivity.class);


//                intent.putExtra(Constant.PLAY_VEDIO_URL,itemList.get(position).getData().getPlayUrl());
//                intent.putExtra(Constant.PLAY_VEDIO_FEED,itemList.get(position).getData().getCover().getBlurred());
//                intent.putExtra(Constant.PLAY_VEDIO_TITLE,itemList.get(position).getData().getTitle());
//                intent.putExtra(Constant.PLAY_VEDIO_ID,itemList.get(position).getData().getId());

                mActivity.startActivity(intent);

            }
        });



    }
}

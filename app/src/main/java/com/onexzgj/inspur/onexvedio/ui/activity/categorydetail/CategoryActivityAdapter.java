package com.onexzgj.inspur.onexvedio.ui.activity.categorydetail;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.CategoryInfo;
import com.onexzgj.onexlibrary.GlobalApplication;


public class CategoryActivityAdapter extends BaseQuickAdapter<CategoryInfo.ItemListBean, BaseViewHolder> {

    public CategoryActivityAdapter() {
        super(R.layout.item_vedio_category, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryInfo.ItemListBean item) {
        if (item.getData().getCover() != null) {
            Glide.with(GlobalApplication.getContext()).load(item.getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.iv_ivc_img));
        }else {
            Glide.with(GlobalApplication.getContext()).load("http://img.kaiyanapp.com/90cc6d8e620ee01888b6d1de74f2b6a8.jpeg?imageMogr2/quality/60/format/jpg").into((ImageView) helper.getView(R.id.iv_ivr_icon));
        }

        if (item.getData().getAuthor().getIcon()!=null)
            Glide.with(GlobalApplication.getContext()).load(item.getData().getAuthor().getIcon()).into((ImageView) helper.getView(R.id.iv_ivc_author));


        helper.setText(R.id.tv_ivc_title,item.getData().getTitle());
        helper.setText(R.id.tv_ivc_tag,item.getData().getCategory());

        if (item.getData().getTags()!=null && item.getData().getTags().size()>2){
            helper.setVisible(R.id.fl_ivc_tag_container,true);

            Glide.with(GlobalApplication.getContext()).load(item.getData().getTags().get(0).getBgPicture()).into((ImageView) helper.getView(R.id.iv_ivc_icon1));
            Glide.with(GlobalApplication.getContext()).load(item.getData().getTags().get(1).getBgPicture()).into((ImageView) helper.getView(R.id.iv_ivc_icon2));

            helper.setText(R.id.tv_ivc_category1,item.getData().getTags().get(0).getName());
            helper.setText(R.id.tv_ivc_category2,item.getData().getTags().get(1).getName());


            helper.setText(R.id.tv_ivc_title1,item.getData().getTags().get(0).getDesc());
            helper.setText(R.id.tv_ivc_title2,item.getData().getTags().get(1).getDesc());

            helper.addOnClickListener(R.id.iv_ivc_share1);
            helper.addOnClickListener(R.id.iv_ivc_share2);


        }else{
            helper.setGone(R.id.fl_ivc_tag_container,false);
        }



    }
}

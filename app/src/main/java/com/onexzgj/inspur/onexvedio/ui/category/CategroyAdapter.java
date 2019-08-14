package com.onexzgj.inspur.onexvedio.ui.category;
import android.app.Application;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.CategoryBean;
import com.onexzgj.inspur.onexvedio.bean.HomeBean.IssueListBean;
import com.onexzgj.onexlibrary.GlobalApplication;

import java.util.List;

public class CategroyAdapter extends BaseQuickAdapter<CategoryBean,BaseViewHolder> {

    public CategroyAdapter() {
        super(R.layout.item_category_view, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, CategoryBean item) {
        helper.setText(R.id.tv_icv_category,item.getName());
        Glide.with(GlobalApplication.getContext()).load(item.getBgPicture()).into((ImageView) helper.getView(R.id.iv_icv_content));
    }
}

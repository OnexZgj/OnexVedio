package com.onexzgj.inspur.onexvedio.ui.category;

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.CategoryBean;
import com.onexzgj.inspur.onexvedio.constant.Constant;
import com.onexzgj.inspur.onexvedio.ui.activity.MainActivity;
import com.onexzgj.inspur.onexvedio.ui.activity.categorydetail.CategoryDetailActivity;
import com.onexzgj.inspur.onexvedio.widget.MyRefreshView;
import com.onexzgj.inspur.onexvedio.widget.SHSwipeRefreshLayout;
import com.onexzgj.onexlibrary.base.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 分类视频
 */
public class CategoryFragment extends BaseFragment<CategoryFragmentImp> implements CategoryContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_fa_content)
    RecyclerView rvFaContent;
    Unbinder unbinder;
    @BindView(R.id.ssrl_refresh)
    SHSwipeRefreshLayout ssrlRefresh;
    private CategroyAdapter mCategroyAdapter;

    public static CategoryFragment getInstance() {
        return new CategoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void initView(View view) {


        View inflate = View.inflate(getContext(), R.layout.item_refresh_head_view, null);

        ImageView ivIrhvRefresh = inflate.findViewById(R.id.iv_irhv_refresh);


        ssrlRefresh.setLoadmoreEnable(false);
//        ssrlRefresh.setHeaderView(new MyRefreshView(getContext()));


        rvFaContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mCategroyAdapter = new CategroyAdapter();
        rvFaContent.setAdapter(mCategroyAdapter);

        mCategroyAdapter.setOnItemClickListener(this);


        ssrlRefresh.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.refresh();
//                ssrlRefresh.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ssrlRefresh.finishRefresh();
//                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
//                    }
//                }, 5000);
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onRefreshPulStateChange(float v, int i) {
                if (i == 1) {
                    ssrlRefresh.setRefreshViewText("下拉刷新...");
                } else if (i == 2) {
                    ssrlRefresh.setRefreshViewText("松开刷新...");
                } else {
                    ssrlRefresh.setRefreshViewText("正在刷新...");
                }
            }

            @Override
            public void onLoadmorePullStateChange(float v, int i) {

            }
        });

        mPresenter.loadData();
    }


    @Override
    protected CategoryFragmentImp initPresenter() {
        return new CategoryFragmentImp(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showCategory(ArrayList<CategoryBean> datas) {

        ssrlRefresh.finishRefresh();
        mCategroyAdapter.setNewData(datas);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(getActivity(), CategoryDetailActivity.class);
        intent.putExtra(Constant.CATEGORY_ID,mCategroyAdapter.getItem(position).getId());
        intent.putExtra(Constant.CATEGORY_BG_ID,mCategroyAdapter.getItem(position).getHeaderImage());
        intent.putExtra(Constant.CATEGORY_NAME,mCategroyAdapter.getItem(position).getName());
        startActivity(intent);
    }
}

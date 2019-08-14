package com.onexzgj.inspur.onexvedio.ui.category;

import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.CategoryBean;
import com.onexzgj.inspur.onexvedio.ui.activity.MainActivity;
import com.onexzgj.inspur.onexvedio.widget.MyRefreshView;
import com.onexzgj.inspur.onexvedio.widget.SHSwipeRefreshLayout;
import com.onexzgj.onexlibrary.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 分类视频
 */
public class CategoryFragment extends BaseFragment<CategoryFragmentImp> implements CategoryContract.View{

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


        View inflate = View.inflate(getContext(),R.layout.item_refresh_head_view, null);

       ImageView ivIrhvRefresh = inflate.findViewById(R.id.iv_irhv_refresh);


        ssrlRefresh.setLoadmoreEnable(false);
//        ssrlRefresh.setHeaderView(new MyRefreshView(getContext()));



        rvFaContent.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mCategroyAdapter = new CategroyAdapter();
        rvFaContent.setAdapter(mCategroyAdapter);

        ssrlRefresh.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                ssrlRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ssrlRefresh.finishRefresh();
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onRefreshPulStateChange(float v, int i) {

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
        if (ssrlRefresh.isRefreshing())
           ssrlRefresh.finishRefresh();
        mCategroyAdapter.setNewData(datas);
    }
}

package com.onexzgj.inspur.onexvedio.ui.rank;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.ui.find.FindAdapter;
import com.onexzgj.inspur.onexvedio.ui.rankinfo.RankInfoFragment;
import com.onexzgj.inspur.onexvedio.utils.IndicatorLineUtil;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 排行Fragment
 */
public class RankFragment extends BaseFragment {

    @BindView(R.id.tab_fr_layout)
    TabLayout tabFrLayout;
    @BindView(R.id.vp_fr_vp)
    ViewPager vpFrVp;


    private ArrayList<String> tabList = new ArrayList<>();

    private ArrayList<BaseFragment> fragments=new ArrayList<>();


    public static RankFragment getInstance() {
        return new RankFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void initView(View view) {
        tabList.add("周排行");
        tabList.add("月排行");
        tabList.add("总排行");

//        fragments.add(RankInfoFragment.getInstance("weekly"));
//        fragments.add(RankInfoFragment.getInstance("monthly"));
//        fragments.add(RankInfoFragment.getInstance("historical"));


        fragments.add(new RankInfoFragment("weekly"));
        fragments.add(new RankInfoFragment("monthly"));
        fragments.add(new RankInfoFragment("historical"));



        vpFrVp.setAdapter(new FindAdapter(getChildFragmentManager(),fragments,tabList));
        tabFrLayout.setupWithViewPager(vpFrVp);


        IndicatorLineUtil.setIndicator(tabFrLayout, 40, 40);

        vpFrVp.setOffscreenPageLimit(fragments.size());

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }



}

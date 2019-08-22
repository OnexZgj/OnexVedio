package com.onexzgj.inspur.onexvedio.ui.find;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.ui.find.follow.FollowFragment;
import com.onexzgj.inspur.onexvedio.ui.rank.RankFragment;
import com.onexzgj.inspur.onexvedio.utils.IndicatorLineUtil;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class FindFragment extends BaseFragment {

    @BindView(R.id.tab_ff_layout)
    TabLayout tabFfLayout;
    @BindView(R.id.vp_ff_vp)
    ViewPager vpFfVp;
    private ArrayList<String> tabList = new ArrayList<>();

    private ArrayList<BaseFragment> fragments=new ArrayList<>();

    public static FindFragment getInstance() {
        return new FindFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {
        tabList.add("关注");
        tabList.add("排行");

        fragments.add(FollowFragment.getInstance());
        fragments.add(RankFragment.getInstance());

        vpFfVp.setAdapter(new FindAdapter(getChildFragmentManager(),fragments,tabList));
        tabFfLayout.setupWithViewPager(vpFfVp);

        IndicatorLineUtil.setIndicator(tabFfLayout, 40, 40);

   }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }


}

package com.onexzgj.inspur.onexvedio.ui.find;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean.IssueListBean;
import com.onexzgj.onexlibrary.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FindAdapter extends FragmentPagerAdapter {

    private  ArrayList<String> titles = new ArrayList<>();
    private  ArrayList<BaseFragment> mFragments = new ArrayList<>();

    public FindAdapter(FragmentManager fm, ArrayList<BaseFragment> mFragments, ArrayList<String> titles) {
        super(fm);
        this.mFragments=mFragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position) ;
    }
}

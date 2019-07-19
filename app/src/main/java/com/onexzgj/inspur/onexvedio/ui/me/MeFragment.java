package com.onexzgj.inspur.onexvedio.ui.me;

import android.content.Intent;
import android.view.View;

import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.ui.activity.VedioActivity;
import com.onexzgj.inspur.onexvedio.ui.home.HomeFragment;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseFragment;

public class MeFragment extends BaseFragment {


    public static MeFragment getInstance() {
        return new MeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
        View tvPlay = view.findViewById(R.id.tv_play);
        tvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VedioActivity.class));
            }
        });

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}

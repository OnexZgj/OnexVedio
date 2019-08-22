package com.onexzgj.inspur.onexvedio.ui.activity.detail;

import android.content.pm.ActivityInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.onexzgj.inspur.onexvedio.R;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.bean.Related;
import com.onexzgj.inspur.onexvedio.constant.Constant;
import com.onexzgj.onexlibrary.base.BaseContract;
import com.onexzgj.onexlibrary.base.BaseMvpActivity;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VedioActivity extends BaseMvpActivity<VedioActivityPresenter> implements VedioActivityContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.detail_player)
    StandardGSYVideoPlayer videoPlayer;
    @BindView(R.id.rv_avd_related)
    RecyclerView rvAvdRelated;
    @BindView(R.id.srl_avd_refresh)
    SwipeRefreshLayout srlAvdRefresh;
    @BindView(R.id.iv_avd_blur)
    ImageView ivAvdBlur;

    private OrientationUtils orientationUtils;


    List<Related.ItemListBean> mDatas = new ArrayList<>();

    /**
     * 播放视频的地址
     */
    private String playVedioUrl = "";
    /**
     * 播放视频的标题
     */
    private String playVedioTitle = "";

    private long playVedioId = 0;

    private String playVedioFeed = "";


    //    private HomeBean.IssueListBean.ItemListBean.DataBean vedioData;
    private VedioAdapter vedioAdapter;

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return new VedioActivityPresenter(this);
    }

    @Override
    protected void initView() {

        if (getIntent() != null) {
            playVedioUrl = getIntent().getStringExtra(Constant.PLAY_VEDIO_URL);
            playVedioTitle = getIntent().getStringExtra(Constant.PLAY_VEDIO_TITLE);
            playVedioId = getIntent().getIntExtra(Constant.PLAY_VEDIO_ID, 0);
            playVedioFeed = getIntent().getStringExtra(Constant.PLAY_VEDIO_FEED);
        }

        if (TextUtils.isEmpty(playVedioUrl))
        videoPlayer.setUp(playVedioUrl, true, playVedioTitle);

        srlAvdRefresh.setOnRefreshListener(this);

        if (TextUtils.isEmpty(playVedioFeed))
        Glide.with(this).load(playVedioFeed).into(ivAvdBlur);

        vedioAdapter = new VedioAdapter(mDatas);

        rvAvdRelated.setLayoutManager(new LinearLayoutManager(this));
        rvAvdRelated.setAdapter(vedioAdapter);

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.bg);
        Glide.with(this).load(playVedioFeed).into(imageView);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();
    }


    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }


    @Override
    protected void initData() {
        mPresenter.loadRelatedData(playVedioId);
    }


    @Override
    public void onRefresh() {
        if (playVedioId != 0)
            mPresenter.loadRelatedData(playVedioId);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_vedio_detail;
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void showRelated(Related relatedBean, int loadType) {

        if (srlAvdRefresh.isRefreshing())
            srlAvdRefresh.setRefreshing(false);
        relatedBean.getItemList().remove(0);
        mDatas = relatedBean.getItemList();

        setLoadDataResult(vedioAdapter, srlAvdRefresh, mDatas, loadType);
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


}

package com.onexzgj.inspur.onexvedio.net;


import com.onexzgj.inspur.onexvedio.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {




    //获取首页第一页数据
    @GET("v2/feed?")
    Observable<HomeBean> getHomeData(@Query("num") int num);



    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    Observable<HomeBean> getMoreHomeData(@Url String url);


}

package com.onexzgj.inspur.onexvedio.net;


import com.onexzgj.inspur.onexvedio.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {




    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<HomeBean> getHomeData();


    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed")
    Observable<HomeBean> getHomeMoreData(@Query("date")  String date , @Query("num") String num );


}

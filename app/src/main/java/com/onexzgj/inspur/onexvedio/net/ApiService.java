package com.onexzgj.inspur.onexvedio.net;


import com.onexzgj.inspur.onexvedio.bean.CategoryBean;
import com.onexzgj.inspur.onexvedio.bean.HomeBean;
import com.onexzgj.inspur.onexvedio.bean.Related;

import java.util.ArrayList;

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

    /**
     * 获取视频分类下的数据
     * @return
     */
    @GET("v4/categories/videoList?")
    Observable<HomeBean> getCategoriesInfo( @Query("id") Long  id);


    /**
     * 获取分类列表
     * @return
     */
    @GET("v4/categories")
    Observable<ArrayList<CategoryBean>> getCategories( );


    /**
     * 获取视频相关信息
     * @param id
     * @return
     */
    @GET("v4/video/related?")
    Observable<Related> getRelated(@Query("id") Long  id);




}

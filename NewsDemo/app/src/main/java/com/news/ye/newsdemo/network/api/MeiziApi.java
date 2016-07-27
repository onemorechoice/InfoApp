package com.news.ye.newsdemo.network.api;

import com.news.ye.newsdemo.data.entity.MeiziData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by YU on 2016/6/23.
 */
public interface MeiziApi {
    @GET("data/福利/10/{page}")
    Observable<MeiziData> getMeizhiData(@Path("page") int page);
}

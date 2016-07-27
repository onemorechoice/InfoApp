package com.news.ye.newsdemo.network.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by YU on 2016/7/21.
 */
public interface NewsDetailApi {
    @GET("{id}/full.html")
    Observable<String>getDetail(@Path("id")String docId);
}

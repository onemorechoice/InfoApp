package com.news.ye.newsdemo.network.api;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by YU on 2016/7/19.
 */
public interface NewsApi {
    @GET("{title}/{id}/{page}-20.html")
    rx.Observable<String> loadNews(@Path("title") String title,@Path("id") String TOP_ID, @Path("page") int page);

}

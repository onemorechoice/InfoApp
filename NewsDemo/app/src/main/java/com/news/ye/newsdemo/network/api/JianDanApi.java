package com.news.ye.newsdemo.network.api;

import com.news.ye.newsdemo.data.entity.JianDan;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by YU on 2016/7/25.
 */
public interface JianDanApi {
    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&")
    rx.Observable<JianDan>getJianDan(@Query("page") int page);
}

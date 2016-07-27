package com.news.ye.newsdemo.ui.iView;

import com.news.ye.newsdemo.data.entity.NewsBean;

import java.util.List;

/**
 * Created by YU on 2016/7/19.
 */
public interface NewsView {
    void Success(List<NewsBean> list);
    void NetWorkDown(String error);
}

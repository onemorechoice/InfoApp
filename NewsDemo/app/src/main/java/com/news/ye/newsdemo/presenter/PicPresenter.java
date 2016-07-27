package com.news.ye.newsdemo.presenter;

import android.content.Context;

/**
 * Created by YU on 2016/7/13.
 */
public interface PicPresenter extends BasePresenter{
    void savePic(Context context, String url, String title);
}

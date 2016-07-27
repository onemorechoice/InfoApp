package com.news.ye.newsdemo.ui.iView;

/**
 * Created by YU on 2016/7/21.
 */
public interface DetailView {
    void showProcessBar();
    void hideProcessBar();
    void getDetailSuccess(String detail);
    void getDetailFail(String error);
}

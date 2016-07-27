package com.news.ye.newsdemo.ui.iView;

import com.news.ye.newsdemo.data.entity.JianDan;

/**
 * Created by YU on 2016/7/25.
 */
public interface JianDanView {
    void getSuccess(JianDan jianDan);
    void getFail();
    void netWorkDown(String error);

}

package com.news.ye.newsdemo.ui.iView;

import com.news.ye.newsdemo.data.entity.Meizi;

import java.util.List;

/**
 * Created by YU on 2016/6/17.
 */
public interface MeiziView {
    void hideProgressDiaglog();
//    void Success(MeiziData bean);
    void Success(List<Meizi> list);
    void Fail();
    void NetWorkDown(String error);
}

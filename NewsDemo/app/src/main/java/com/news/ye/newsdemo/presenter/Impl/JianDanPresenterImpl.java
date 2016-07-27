package com.news.ye.newsdemo.presenter.Impl;

import com.news.ye.newsdemo.data.entity.JianDan;
import com.news.ye.newsdemo.network.NetWork;
import com.news.ye.newsdemo.presenter.JianDanPresenter;
import com.news.ye.newsdemo.ui.iView.JianDanView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YU on 2016/7/25.
 */
public class JianDanPresenterImpl extends BasePresenterImpl implements JianDanPresenter {
    private JianDanView jianDanView;

    public JianDanPresenterImpl(JianDanView jianDanView){
        this.jianDanView=jianDanView;
    }

    @Override
    public void getJianDanList(int page) {
        Subscription subscription=
                NetWork.getJianDanApi()
                       .getJianDan(page)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Action1<JianDan>() {
                           @Override
                           public void call(JianDan jianDan) {
                               if (jianDan.getStatus().equals("ok")){
                                 jianDanView.getSuccess(jianDan);
                               }
                               else{
                                 jianDanView.getFail();
                               }
                           }
                       }, new Action1<Throwable>() {
                           @Override
                           public void call(Throwable throwable) {
                             jianDanView.netWorkDown(throwable.getMessage());
                           }
                       });
         addSubscription(subscription);

    }
}

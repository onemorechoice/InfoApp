package com.news.ye.newsdemo.presenter.Impl;

import com.news.ye.newsdemo.data.entity.NewsDetailBean;
import com.news.ye.newsdemo.network.NetWork;
import com.news.ye.newsdemo.presenter.NewsDetailPresenter;
import com.news.ye.newsdemo.ui.iView.DetailView;
import com.news.ye.newsdemo.util.NewsJsonUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YU on 2016/7/21.
 */
public class NewsDetailPresenterImpl extends BasePresenterImpl implements NewsDetailPresenter {
    private DetailView detailView;


    public NewsDetailPresenterImpl(DetailView detailView){
        this.detailView=detailView;
    }

    @Override
    public void getNewsDetail(final String docId) {
        detailView.showProcessBar();
        Subscription subscription=
                NetWork.getNewsDetalApi()
                       .getDetail(docId)
                       .map(new Func1<String, String>() {
                           @Override
                           public String call(String s) {
                               NewsDetailBean bean= NewsJsonUtils.readJsonNewsDetailBeans(s,docId);
                               return bean.getBody();
                           }
                       })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        detailView.hideProcessBar();
                        detailView.getDetailSuccess(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                      detailView.hideProcessBar();
                      detailView.getDetailFail(throwable.getMessage());
                    }
                });
        addSubscription(subscription);
    }
}

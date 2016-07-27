package com.news.ye.newsdemo.presenter.Impl;

import com.news.ye.newsdemo.data.entity.NewsBean;
import com.news.ye.newsdemo.network.NetWork;
import com.news.ye.newsdemo.presenter.NewsPresenter;
import com.news.ye.newsdemo.ui.iView.NewsView;
import com.news.ye.newsdemo.util.Const;
import com.news.ye.newsdemo.util.NewsJsonUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YU on 2016/7/19.
 */
public class NewsPresentImpl extends BasePresenterImpl implements NewsPresenter {
    private String top_id;
    private String title;
    private NewsView newsView;
    public NewsPresentImpl(NewsView newsView){
        if (newsView==null)
            throw new IllegalArgumentException("weixinFragment must not be null");
        this.newsView =newsView;
    }

    @Override
    public void getNewsList(int type,int page) {
        getHostUrl(type);
        Subscription subscription=
                NetWork.getNewsApi(Const.HOST)
                        .loadNews(title,top_id,page)
                        .map(new Func1<String, List<NewsBean>>() {
                            @Override
                            public List<NewsBean> call(String s) {
                                return NewsJsonUtils.readJsonNewsBeans(s,top_id);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<NewsBean>>() {
                            @Override
                            public void call(List<NewsBean> newsBeen) {
                               newsView.Success(newsBeen);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                newsView.NetWorkDown(throwable.getMessage());
                            }
                        });
                    addSubscription(subscription);

    }
    private void getHostUrl(int mType) {
        switch (mType){
            case 0:
                top_id=Const.TOP_ID;
                title=Const.TOP_URL;
                break;
            case 1:
                top_id=Const.NBA_ID;
                title=Const.COMMON_URL;
                break;
            case 2:
                top_id=Const.CAR_ID;
                title=Const.COMMON_URL;
                break;
            case 3:
                top_id=Const.JOKE_ID;
                title=Const.COMMON_URL;
                break;
        }
    }
}

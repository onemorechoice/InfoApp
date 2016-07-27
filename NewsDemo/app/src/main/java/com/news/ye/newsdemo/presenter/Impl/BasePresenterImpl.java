package com.news.ye.newsdemo.presenter.Impl;

import com.news.ye.newsdemo.presenter.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by YU on 2016/6/24.
 */
public class BasePresenterImpl implements BasePresenter {
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s){
        if (this.mCompositeSubscription==null){
            this.mCompositeSubscription=new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubcrible() {
         if (this.mCompositeSubscription!=null){
             this.mCompositeSubscription.unsubscribe();
         }
    }
}

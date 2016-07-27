package com.news.ye.newsdemo.presenter.Impl;

import android.content.Context;
import android.net.Uri;

import com.news.ye.newsdemo.model.PicModelImpl;
import com.news.ye.newsdemo.presenter.PicPresenter;
import com.news.ye.newsdemo.ui.iView.PicView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by YU on 2016/7/13.
 */
public class PicPresenterImpl extends BasePresenterImpl implements PicPresenter{
    private PicView picView;
    private PicModelImpl picModelImpl;

    public PicPresenterImpl(PicView picView){
        this.picView=picView;
        picModelImpl=new PicModelImpl();
    }


    @Override
    public void savePic(Context context, String url, String title) {
        Subscription subscription=
                picModelImpl.savePicToPhone(context,url,title)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>() {
                    @Override
                    public void call(Uri uri) {
                      picView.savePictureSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                     picView.FailToSave(throwable);
                    }
                });
        addSubscription(subscription);
    }
}

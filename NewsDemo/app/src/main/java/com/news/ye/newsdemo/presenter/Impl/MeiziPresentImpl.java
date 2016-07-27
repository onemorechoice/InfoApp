package com.news.ye.newsdemo.presenter.Impl;

import com.news.ye.newsdemo.data.entity.MeiziData;
import com.news.ye.newsdemo.data.entity.Meizi;
import com.news.ye.newsdemo.network.NetWork;
import com.news.ye.newsdemo.presenter.MeiziPresenter;
import com.news.ye.newsdemo.ui.iView.MeiziView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YU on 2016/6/23.
 */
public class MeiziPresentImpl extends BasePresenterImpl implements MeiziPresenter {
    private MeiziView meiziView;
    public MeiziPresentImpl(MeiziView meiziView){
        if (meiziView==null)
            throw new IllegalArgumentException("weixinFragment must not be null");
        this.meiziView = meiziView;
    }

    @Override
    public void getMeizi(int page) {
       Subscription subscription
            = NetWork.getMeiziApi()
                     .getMeizhiData(page)
                     .map(new Func1<MeiziData,List<Meizi>>() {
                         @Override
                         public List<Meizi>call(MeiziData meiziData) {
                             List<Meizi> list=meiziData.getResults();
                             return list;
                         }
                     })
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Observer<List<Meizi>>() {
                         @Override
                         public void onCompleted() {

                         }

                         @Override
                         public void onError(Throwable e) {
                             meiziView.hideProgressDiaglog();
                             meiziView.NetWorkDown(e.getMessage());
                         }

                         @Override
                         public void onNext(List<Meizi> meiziData) {
                             meiziView.hideProgressDiaglog();
//                            if (meiziData.isError()){
//                                meiziView.Fail();
//                            }else{
                                meiziView.Success(meiziData);
//                            }
                         }
                     });
         addSubscription(subscription);
    }
}

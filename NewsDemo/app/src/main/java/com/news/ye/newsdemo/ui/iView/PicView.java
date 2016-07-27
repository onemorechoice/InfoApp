package com.news.ye.newsdemo.ui.iView;

/**
 * Created by YU on 2016/7/13.
 */
public interface PicView {
    void savePictureSuccess();
    void FailToSave(Throwable e);

    void sharePictureSuccess();
    void FailtoFail();

}

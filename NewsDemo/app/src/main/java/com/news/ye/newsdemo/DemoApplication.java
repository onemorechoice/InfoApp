package com.news.ye.newsdemo;

import android.content.Context;

import com.news.ye.newsdemo.custom_arr.CollapsingToolbarLayoutArr;
import com.news.ye.newsdemo.custom_arr.TabLayoutIndicatorAttr;
import com.news.ye.newsdemo.util.Toasts;

import solid.ren.skinlibrary.base.SkinBaseApplication;
import solid.ren.skinlibrary.config.SkinConfig;

/**
 * Created by YU on 2016/6/24.
 */
public class DemoApplication extends SkinBaseApplication {

    public static DemoApplication demoApplication=null;
    @Override
    public void onCreate() {
        super.onCreate();
        demoApplication=this;
        Toasts.register(this);
        SkinConfig.setCanChangeStatusColor(true);
        SkinConfig.addSupportAttr("contentScrimColor",new CollapsingToolbarLayoutArr());
        SkinConfig.addSupportAttr("tabLayoutIndicator", new TabLayoutIndicatorAttr());
    }

    public static Context getContext(){
        return demoApplication;
    }
}

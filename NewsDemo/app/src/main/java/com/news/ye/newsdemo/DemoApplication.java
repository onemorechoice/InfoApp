package com.news.ye.newsdemo;

import android.app.Application;
import android.content.Context;

import com.news.ye.newsdemo.util.Toasts;

/**
 * Created by YU on 2016/6/24.
 */
public class DemoApplication extends Application {

    public static DemoApplication demoApplication=null;
    @Override
    public void onCreate() {
        super.onCreate();
        demoApplication=this;
        Toasts.register(this);
    }

    public static Context getContext(){
        return demoApplication;
    }
}

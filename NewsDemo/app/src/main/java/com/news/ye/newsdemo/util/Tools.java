package com.news.ye.newsdemo.util;

import android.content.Context;

/**
 * Created by Feng on 2016/7/30.
 */
public class Tools {

    public static int dpTopx(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}

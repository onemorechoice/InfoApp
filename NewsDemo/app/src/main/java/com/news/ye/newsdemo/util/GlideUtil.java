package com.news.ye.newsdemo.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.news.ye.newsdemo.R;

/**
 * Created by YU on 2016/6/27.
 */
public class GlideUtil {

    /**
     * glide加载图片
     *
     * @param view view
     * @param url url
     */
    public static void display(ImageView view, String url) {
        displayUrl(view, url, R.drawable.loading);
    }


    /**
     * glide加载图片
     *
     * @param view view
     * @param url url
     * @param defaultImage defaultImage
     */
    private static void displayUrl(final ImageView view, String url,
                                   @DrawableRes int defaultImage) {
        // 不能崩
        if (view == null) {
            Log.e("GlideUtils","GlideUtils -> display -> imageView is null");
            return;
        }
        Context context = view.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultImage)
                    .crossFade()
                    .centerCrop()
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void displayNative(final ImageView view, @DrawableRes int resId) {
        // 不能崩
        if (view == null) {
            Log.e("GlideUtils ","GlideUtils -> display -> imageView is null");
            return;
        }
        Context context = view.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(resId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .centerCrop()
                    .into(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

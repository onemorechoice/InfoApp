package com.news.ye.newsdemo.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by YU on 2016/4/20.
 */
public class GlideModuleConfig implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"MY_CACHE_LOCATION",100*1024*1024));
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"MY_CACHE_LOCATION",100*1024*1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
           glide.setMemoryCategory(MemoryCategory.NORMAL);
    }
}

package com.news.ye.newsdemo.network;


import android.util.Log;

import com.news.ye.newsdemo.DemoApplication;
import com.news.ye.newsdemo.network.api.JianDanApi;
import com.news.ye.newsdemo.network.api.MeiziApi;
import com.news.ye.newsdemo.network.api.NewsApi;
import com.news.ye.newsdemo.network.api.NewsDetailApi;
import com.news.ye.newsdemo.util.Const;
import com.news.ye.newsdemo.util.NetWorkUtil;
import com.news.ye.newsdemo.util.ScalarsConverterFactory;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YU on 2016/6/23.
 */
public class NetWork {

    public NetWork(){};

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!NetWorkUtil.isNetWorkAvailable(DemoApplication.getContext())){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.i("noNet","没网");
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetWorkAvailable(DemoApplication.getContext())) {
                String cacheControl = request.cacheControl().toString();
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                Log.i("TAG","离线缓存");
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }

        }
    };
    private static  HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.i("RestLogging", message);
        }
    });
    private static File httpCacheDirectory = new File(DemoApplication.getContext().getCacheDir(), "itCache");

    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    private static CookieHandler cookieHandler = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static MeiziApi meiziApi;
    private static NewsApi newsApi;
    private static NewsDetailApi newsDetailApi;
    private static JianDanApi jianDanApi;
    private static final Object monitor = new Object();


    public static MeiziApi getMeiziApi() {
        synchronized (monitor) {
            if (meiziApi == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl("http://gank.io/api/")
                        .addConverterFactory(gsonConverterFactory)
                        .addCallAdapterFactory(rxJavaCallAdapterFactory)
                        .build();
                meiziApi = retrofit.create(MeiziApi.class);
            }
            return meiziApi;
        }
    }

    public static NewsApi getNewsApi(String host) {
        synchronized (monitor) {
            if (newsApi == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(host)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(rxJavaCallAdapterFactory)
                        .build();
                newsApi= retrofit.create(NewsApi.class);
            }
            return newsApi;
        }
    }

    public static NewsDetailApi getNewsDetalApi() {
        synchronized (monitor) {
            if (newsDetailApi == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(Const.HOST)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(rxJavaCallAdapterFactory)
                        .build();
                newsDetailApi= retrofit.create(NewsDetailApi.class);
            }
            return newsDetailApi;
        }
    }
    public static JianDanApi getJianDanApi() {
        synchronized (monitor) {
            if (jianDanApi == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(Const.JIAN_DAN)
                        .addConverterFactory(gsonConverterFactory)
                        .addCallAdapterFactory(rxJavaCallAdapterFactory)
                        .build();
                jianDanApi = retrofit.create(JianDanApi.class);
            }
            return jianDanApi;
        }
    }
}

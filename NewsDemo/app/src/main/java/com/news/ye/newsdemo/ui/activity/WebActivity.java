package com.news.ye.newsdemo.ui.activity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.ui.base.ToolbarActivity;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WebActivity extends ToolbarActivity {
    private Unbinder umBinder;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    NumberProgressBar progressBar;
    private String webUrl;
    private String webTitle;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        umBinder = ButterKnife.bind(this);
        webUrl=getIntent().getStringExtra("url");
        webTitle=getIntent().getStringExtra("title");
        setTitle(webTitle);
        showInfo();
    }

    private void showInfo() {
        //WebView的一些常规设定
        WebSettings settings=webView.getSettings();
        //让WebView支持JavaScript脚本
        settings.setJavaScriptEnabled(true);
        //设置webView自适应屏幕大小
        settings.setLoadWithOverviewMode(true);
        //将图片调整到适合webView的大小
        settings.setUseWideViewPort(true);
       //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
       //支持通过JS打开新窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
       //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        settings.setAppCacheEnabled(true);
        //webView能缩放大小
        settings.setSupportZoom(true);
        //对于WebChromeClient，主要处理关于脚本的执行，或progress 等操作
        webView.setWebChromeClient(new ChromeClient());
       // 而WebViewClient 主要处理关于页面跳转，页面请求等操作
        webView.setWebViewClient(new WebClient());
//        webUrl="http://jandan.net/2016/07/27/bitcoin-not-money.html";
        webView.loadUrl(webUrl);
//        webTitle="美国法官：比特币不是钱，怎么用咱不管";


    }


    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    private class WebClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (url.contains("jandan.net")) {
                injectCSS("jiandan.css");
            }
        }
    }
    private void injectCSS(String filename) {
        try {
            InputStream inputStream = this.getAssets().open(filename);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView!=null)webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView!=null)webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView!=null)webView.destroy();
        umBinder.unbind();
    }
}

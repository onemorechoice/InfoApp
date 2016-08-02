package com.news.ye.newsdemo.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.news.ye.newsdemo.R;

import solid.ren.skinlibrary.base.SkinBaseActivity;

/**
 * Created by YU on 2016/6/27.
 */
public class BaseActivity extends SkinBaseActivity {

    private SwipeBackLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.base, null);
        layout.attachToActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}



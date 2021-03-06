package com.news.ye.newsdemo.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.NewsBean;
import com.news.ye.newsdemo.presenter.Impl.NewsDetailPresenterImpl;
import com.news.ye.newsdemo.presenter.NewsDetailPresenter;
import com.news.ye.newsdemo.ui.base.BaseActivity;
import com.news.ye.newsdemo.ui.iView.DetailView;
import com.news.ye.newsdemo.util.ImmersedStatusbarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailActivity extends BaseActivity implements DetailView{
   @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout mCollapsinglayout;
    @BindView(R.id.detail_pic)
    ImageView iv;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.web_layout)
    LinearLayout web_layout;
    @BindView(R.id.html_tx)
    TextView html_tv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nestScrollView)
    NestedScrollView nestScrollView;
//    @BindView(R.id.htmlContent)
//    private HtmlTextView htmlTextView;
    private NewsBean newsBean;
    private NewsDetailPresenter newsDetailPresenter;
    private Unbinder umBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
        setContentView(R.layout.activity_detal);
        umBinder= ButterKnife.bind(this);
        newsBean= (NewsBean) getIntent().getSerializableExtra("news");
        showDetail();
        setListener();
    }

    private void showDetail() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        dynamicAddView(mCollapsinglayout,"contentScrimColor", R.color.colorPrimary);
        mCollapsinglayout.setTitle(newsBean.getTitle());
        Glide.with(this)
                .load(newsBean.getImgsrc())
                .error(R.drawable.error)
                .placeholder(R.drawable.loading)
                .into(iv);
        newsDetailPresenter=new NewsDetailPresenterImpl(this);
        newsDetailPresenter.getNewsDetail(newsBean.getDocid());

    }
    private void setListener() {
     fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           nestScrollView.scrollTo(0,0);
         }
     });


    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void showProcessBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProcessBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDetailSuccess(String detail) {
        CharSequence charSequence = Html.fromHtml(detail);
        html_tv.setText(charSequence);
//          htmlTextView.setHtmlFromString(detail, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void getDetailFail(String error) {
//        Toasts.showShort(error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        umBinder.unbind();
        newsDetailPresenter.unsubcrible();

    }
    public static int dpTopx(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}

package com.news.ye.newsdemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.presenter.Impl.MainPresentImpl;
import com.news.ye.newsdemo.presenter.MainPresenter;
import com.news.ye.newsdemo.ui.base.BaseActivity;
import com.news.ye.newsdemo.ui.fragment.ImageFragment;
import com.news.ye.newsdemo.ui.fragment.JianDanFragment;
import com.news.ye.newsdemo.ui.fragment.NewsFragment;
import com.news.ye.newsdemo.ui.fragment.ThemeFragment;
import com.news.ye.newsdemo.ui.iView.MainView;

public class MainActivity extends BaseActivity implements MainView{
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private MainPresenter mainPresenter;
    private long exitTime = 0;
    //Test Git pull and push 2016/07/27 23:39



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter=new MainPresentImpl(this);
        Test();

    }

    private void Test() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","onStart456");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","onStop");
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        dynamicAddView( mToolbar, "background", R.color.colorPrimary);

        //绑定抽屉的标准写法
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //绑定抽屉侧滑菜单
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);
        mNavigationView.setItemBackground(getResources().getDrawable(R.drawable.navigation_selector));
        dynamicAddView(mNavigationView.getHeaderView(0), "background",R.color.colorPrimary);
        switch2News();

    }


    public void setupDrawerContent(NavigationView navigationView) {
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem item) {
                 mainPresenter.switchNavigation(item.getItemId());
                 item.setCheckable(true);
                 mDrawerLayout.closeDrawers();
                 return true;
             }
         });

    }

    @Override
    public void switch2Images() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new ImageFragment()).commit();
        mToolbar.setTitle("福利");
    }

    @Override
    public void switch2News() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsFragment()).commit();
        mToolbar.setTitle("新闻");
    }

    @Override
    public void switch2JianDan() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new JianDanFragment()).commit();
        mToolbar.setTitle("煎蛋");
    }

    @Override
    public void switch2Theme() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new ThemeFragment()).commit();
        mToolbar.setTitle("主题换肤");
    }


    //再按一次退出的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}

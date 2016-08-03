package com.news.ye.newsdemo.presenter.Impl;

import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.presenter.MainPresenter;
import com.news.ye.newsdemo.ui.iView.MainView;

/**
 * Created by YU on 2016/7/3.
 */
public class MainPresentImpl implements MainPresenter{
    private MainView mainView;
    public MainPresentImpl(MainView mainView){
        this.mainView=mainView;
    }

    @Override
    public void switchNavigation(int id) {
      switch (id){
          case R.id.navigation_item_news:
              mainView.switch2News();
              break;
          case R.id.navigation_item_meizi:
              mainView.switch2Images();
              break;
          case R.id.navigation_item_jiandan:
              mainView.switch2JianDan();
              break;
          case R.id.navigation_theme:
              mainView.switch2Theme();
              break;
          case R.id.navigation_about:
              mainView.switch2About();
              break;
      }
    }
}

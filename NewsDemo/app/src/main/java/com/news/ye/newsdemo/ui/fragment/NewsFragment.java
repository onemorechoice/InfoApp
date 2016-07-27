package com.news.ye.newsdemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.ui.adapter.MyPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;
    private Unbinder umBinder;
    @BindView(R.id.tab_layout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news, container, false);
        // Inflate the layout for this fragment
        umBinder= ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText("头条"));
        mTablayout.addTab(mTablayout.newTab().setText("NBA"));
        mTablayout.addTab(mTablayout.newTab().setText("汽车"));
        mTablayout.addTab(mTablayout.newTab().setText("笑话"));
        mTablayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter=new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP),"头条");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA),"NBA");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS),"汽车");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES),"笑话");
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        umBinder.unbind();
    }
}

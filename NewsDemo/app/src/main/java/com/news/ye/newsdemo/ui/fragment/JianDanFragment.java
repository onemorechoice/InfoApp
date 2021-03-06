package com.news.ye.newsdemo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.JianDan;
import com.news.ye.newsdemo.presenter.Impl.JianDanPresenterImpl;
import com.news.ye.newsdemo.presenter.JianDanPresenter;
import com.news.ye.newsdemo.ui.activity.WebActivity;
import com.news.ye.newsdemo.ui.adapter.JianDanAdapter;
import com.news.ye.newsdemo.ui.base.BaseFragment;
import com.news.ye.newsdemo.ui.iView.JianDanView;
import com.news.ye.newsdemo.util.Toasts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class JianDanFragment extends BaseFragment implements JianDanView{


    private Unbinder umBinder;
    private JianDanPresenter jianDanPresenter;
    private int page=1;
    @BindView(R.id.xlist)
    XRecyclerView xList;
    @BindView(R.id.time_out)
    RelativeLayout time_out;
    private ArrayList<JianDan.PostsEntity> listData;
    private JianDanAdapter adapter;

    public JianDanFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_jian_dan, container, false);
        umBinder= ButterKnife.bind(this,view);
        jianDanPresenter=new JianDanPresenterImpl(this);
        initData();
        return view;
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xList.setLayoutManager(layoutManager);
        xList.setRefreshProgressStyle(ProgressStyle.BallScaleMultiple);
        xList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xList.setArrowImageView(R.drawable.iconfont_downgrey);
        xList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                onRefreshList();
            }

            @Override
            public void onLoadMore() {
                page += 1;
                jianDanPresenter.getJianDanList(page);
            }
        });
        listData=new ArrayList<>();
        adapter=new JianDanAdapter(getActivity(),listData);
        adapter.setOnItemClickListener(mOnItemClickListener);
        xList.setAdapter(adapter);
        xList.setRefreshing(true);
        time_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefreshList();
            }
        });
    }
    JianDanAdapter.OnItemClickListener mOnItemClickListener=new JianDanAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
                  JianDan.PostsEntity jianDan=adapter.getItem(position);
                  Intent intent=new Intent(getActivity(), WebActivity.class);
                  intent.putExtra("url",jianDan.getUrl());
                  intent.putExtra("title",jianDan.getTitle());
                  startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        umBinder.unbind();
        jianDanPresenter.unsubcrible();
    }

    @Override
    public void getSuccess(JianDan jianDan) {
        xList.setVisibility(View.VISIBLE);
        time_out.setVisibility(View.GONE);
        if (page==1){
            if (listData!=null){
                listData.clear();
            }
        }
        listData.addAll(jianDan.getPosts());
        adapter.notifyDataSetChanged();
        xList.refreshComplete();
    }

    @Override
    public void getFail() {
        Toasts.showShort("服务器出错了");
        xList.setVisibility(View.GONE);
        time_out.setVisibility(View.VISIBLE);
    }

    @Override
    public void netWorkDown(String error) {
        xList.setVisibility(View.GONE);
        time_out.setVisibility(View.VISIBLE);
        Toasts.showShort(error);
    }

    private void onRefreshList(){
        page=1;
        jianDanPresenter.getJianDanList(page);
        xList.setLoadingMoreEnabled(true);
    }
}

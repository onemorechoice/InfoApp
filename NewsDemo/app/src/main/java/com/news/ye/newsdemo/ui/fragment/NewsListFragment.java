package com.news.ye.newsdemo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.NewsBean;
import com.news.ye.newsdemo.presenter.Impl.NewsPresentImpl;
import com.news.ye.newsdemo.presenter.NewsPresenter;
import com.news.ye.newsdemo.ui.activity.DetailActivity;
import com.news.ye.newsdemo.ui.adapter.NewsAdapter;
import com.news.ye.newsdemo.ui.base.LazyFragment;
import com.news.ye.newsdemo.ui.iView.NewsView;
import com.news.ye.newsdemo.util.Const;
import com.news.ye.newsdemo.util.Toasts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends LazyFragment implements NewsView{
    private Unbinder umBinder;
    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;
    private NewsPresenter newsPresenter;

    private ArrayList<NewsBean> listData;
    private NewsAdapter adapter;
    @BindView(R.id.xlist)
    XRecyclerView xList;
    @BindView(R.id.time_out)
    RelativeLayout time_out;


    public static NewsListFragment newInstance(int type){
        Bundle args=new Bundle();
        NewsListFragment fragment=new NewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","onCreate");
        newsPresenter=new NewsPresentImpl(this);
        mType=getArguments().getInt("type");

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news_list, container, false);
        umBinder= ButterKnife.bind(this,view);
        return view;
    }


    private void setListListener() {
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
                    pageIndex += Const.PAZE_SIZE;
                    newsPresenter.getNewsList(mType, pageIndex);
            }
        });
        listData=new ArrayList<>();
        adapter=new NewsAdapter(getActivity(),listData);
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
    private NewsAdapter.OnItemClickListener mOnItemClickListener=new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Log.i("position",position+"");
           NewsBean newsBean=adapter.getItem(position-1);
            Intent intent=new Intent(getActivity(), DetailActivity.class);
               intent.putExtra("news",newsBean);
            View translationView=view.findViewById(R.id.ivNews);
            ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation
                    (getActivity(),translationView,getString(R.string.transition_img));
            try {
                ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                startActivity(intent);
            }
        }
    };

    @Override
    protected void initData() {
        setListListener();
    }



    @Override
    public void Success(List<NewsBean> list) {
        xList.setVisibility(View.VISIBLE);
        time_out.setVisibility(View.GONE);
        if (pageIndex==0){
            if (listData!=null){
                listData.clear();
            }
        }
        listData.addAll(list);
        adapter.notifyDataSetChanged();
        xList.refreshComplete();
    }

    @Override
    public void NetWorkDown(String error) {
        Log.i("error",error);

        xList.refreshComplete();
        xList.setLoadingMoreEnabled(false);
        if (pageIndex!=0) {
            pageIndex -= Const.PAZE_SIZE;
        }
        if (error.equals("HTTP 403 Forbidden")){
            Toasts.showShort("没有更多信息可以加载可以试试刷新看看~~~");
        }else if (error.contains("after")){
            xList.setVisibility(View.GONE);
            time_out.setVisibility(View.VISIBLE);
        }
        else{
            Toasts.showShort(error);
        }

    }
    private void onRefreshList(){
        pageIndex=0;
        newsPresenter.getNewsList(mType,pageIndex);
        xList.setLoadingMoreEnabled(true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        umBinder.unbind();
        newsPresenter.unsubcrible();
    }

}

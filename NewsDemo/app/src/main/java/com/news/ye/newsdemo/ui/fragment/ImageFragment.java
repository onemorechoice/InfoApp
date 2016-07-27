package com.news.ye.newsdemo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.Meizi;
import com.news.ye.newsdemo.presenter.Impl.MeiziPresentImpl;
import com.news.ye.newsdemo.presenter.MeiziPresenter;
import com.news.ye.newsdemo.ui.activity.PictureActivity;
import com.news.ye.newsdemo.ui.adapter.MeiziAdapter;
import com.news.ye.newsdemo.ui.iView.MeiziView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment implements MeiziView {

//    ,SwipeRefreshLayout.OnRefreshListener
    private Unbinder umBinder;
    private MeiziPresenter mMeiziPresenter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<Meizi> list=new ArrayList<>();
    private MeiziAdapter adapter;
    private int currentPage=1;
    private int lastVisibleItem;
    //是否正在加载更多的标志
    private boolean isMoreLoading = false;
    private int DataSize;
    private int[] lastPositions;

    @BindView(R.id.recycler_view)
    RefreshRecyclerView recyclerView;
//    @BindView(R.id.swipeToLoadLayout)
//    SwipeRefreshLayout swipeRefreshLayout;

    public ImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image, container, false);
        umBinder= ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMeiziPresenter=new MeiziPresentImpl(this);
        initView();
//        setListener();
    }



    private void initView() {
//      swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary,
//              R.color.colorPrimaryDark, R.color.colorAccent,
//              R.color.navigation_selected_color);
//        swipeRefreshLayout.setOnRefreshListener(this);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter=new MeiziAdapter(list,getActivity());
//        recyclerView.setAdapter(adapter);
        //利用了用footer和header的第三方控件控制滑动效果和逻辑
        RecyclerViewManager.with(adapter, staggeredGridLayoutManager)
                            .setMode(RecyclerMode.BOTH)
                            .setOnBothRefreshListener(new OnBothRefreshListener() {
                                @Override
                                public void onPullDown() {
                                    currentPage=1;
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                    mMeiziPresenter.getMeizi(currentPage);
                                }

                                @Override
                                public void onLoadMore() {
                                    isMoreLoading=true;
                                    currentPage++;
                                    mMeiziPresenter.getMeizi(currentPage);
                                    isMoreLoading=false;
                                }
                            })
//                .setOnItemClickListener(new RefreshRecyclerViewAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
//                        Toast.makeText(getActivity(), "item" + position, Toast.LENGTH_SHORT).show();
//                    }
//                })
                .into(recyclerView, getActivity());
        adapter.setOnItemClickListener(onItemClickListener);
        mMeiziPresenter.getMeizi(1);
    }
    private MeiziAdapter.OnItemClickListener onItemClickListener=new MeiziAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position,View iv) {
            Meizi meizi=adapter.getItem(position);
               Intent intent=new Intent(getActivity(), PictureActivity.class);
               intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE,meizi.getCreatedAt());
               intent.putExtra(PictureActivity.EXTRA_IMAGE_URL,meizi.getUrl());
            //渐变打开的5.0动画
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(), iv, PictureActivity.TRANSIT_PIC);
            try {
                ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                startActivity(intent);
            }

        }
    };
    private void setListener() {
       recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           private boolean toLast = false;
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
               int visibleItemCount = staggeredGridLayoutManager.getChildCount();
               int totalItemCount = staggeredGridLayoutManager.getItemCount();
               if (visibleItemCount > 0&&newState==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem  >= totalItemCount - 1){
                       isMoreLoading=true;
                       currentPage++;
                       mMeiziPresenter.getMeizi(currentPage);
                       isMoreLoading=false;
               }
           }

           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               if (lastPositions == null) {
                   lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
               }
               staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
               lastVisibleItem = findMax(lastPositions);

           }
       });
    }

    //找到数组中的最大值
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        umBinder.unbind();
        mMeiziPresenter.unsubcrible();
    }


    @Override
    public void hideProgressDiaglog() {
//       if (swipeRefreshLayout!=null){
//           swipeRefreshLayout.setRefreshing(false);
//       }
    }

    @Override
    public void Success(List<Meizi> bean) {
//        DataSize = bean.getResults().size();
//        Log.i("bean",bean.toString());
//        if (DataSize < 10) {
//            adapter.setGoneFooter();
//        }
       list.addAll(bean);
       recyclerView.onRefreshCompleted();
       adapter.notifyDataSetChanged();
    }

    @Override
    public void Fail() {
        Toast.makeText(getActivity(),"发生未知错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void NetWorkDown(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onRefresh() {
//      currentPage=1;
//        list.clear();
//        adapter.notifyDataSetChanged();
//        mMeiziPresenter.getMeizi(currentPage);
//    }
}

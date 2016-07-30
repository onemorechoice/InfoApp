package com.news.ye.newsdemo.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.NewsBean;
import com.news.ye.newsdemo.data.entity.Theme;

import java.util.List;

/**
 * Created by Feng on 2016/7/30.
 */
public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Theme> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public ThemeAdapter(Context context,List<Theme> list){
        this.mContext=context;
        this.mDatas=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
           Theme theme=mDatas.get(position);
           if (theme == null) {
            return;
            }
           ((ItemViewHolder)holder).img.setImageResource(theme.getImg());
           ((ItemViewHolder)holder).name.setText(theme.getName());
           ((ItemViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (mOnItemClickListener!=null){
                       mOnItemClickListener.onItemClick(v,position);
                   }
               }
           });
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public Theme getItem(int position) {
        return mDatas == null ? null : mDatas.get(position);
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        public ItemViewHolder(View itemView) {
            super(itemView);
           img=(ImageView)itemView.findViewById(R.id.img);
           name=(TextView)itemView.findViewById(R.id.name);
        }
    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//            if (parent.getChildLayoutPosition(view) %3==0) {
//                outRect.left = 0;
//            }
        }

    }
}

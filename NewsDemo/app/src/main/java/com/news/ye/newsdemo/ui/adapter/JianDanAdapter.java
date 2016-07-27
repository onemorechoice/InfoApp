package com.news.ye.newsdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.JianDan;

import java.util.List;

/**
 * Created by YU on 2016/7/25.
 */
public class JianDanAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<JianDan.PostsEntity> mData;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    public JianDanAdapter (Context context,List<JianDan.PostsEntity> data) {
        this.mContext = context;
        this.mData = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        JianDan.PostsEntity jianDan=mData.get(position);
        if (jianDan == null) {
            return;
        }
        ((ItemViewHolder) holder).mTitle.setText(jianDan.getTitle());
        ((ItemViewHolder) holder).mDesc.setText(jianDan.getDate());
        Glide.with(mContext)
                .load(jianDan.getCustom_fields().getThumb_c().get(0))
                .error(R.drawable.error)
                .placeholder(R.drawable.loading)
                .into(((ItemViewHolder) holder).mNewsImg);

        ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public JianDan.PostsEntity getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder  {

        public TextView mTitle;
        public TextView mDesc;
        public ImageView mNewsImg;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mDesc = (TextView) v.findViewById(R.id.tvDesc);
            mNewsImg = (ImageView) v.findViewById(R.id.ivNews);
//            v.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if(mOnItemClickListener != null) {
//                mOnItemClickListener.onItemClick(view, this.getPosition());
//            }
//        }
    }
}

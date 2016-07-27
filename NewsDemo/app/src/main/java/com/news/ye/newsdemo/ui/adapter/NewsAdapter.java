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
import com.news.ye.newsdemo.data.entity.NewsBean;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //普通ITEM
    protected static final int ITEM_VIEW=1;
    private List<NewsBean> mData;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public NewsAdapter(Context context,List<NewsBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setmDate(List<NewsBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        if (viewType == ITEM_VIEW) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new ItemViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            NewsBean news = mData.get(position);
            if (news == null) {
                return;
            }
            ((ItemViewHolder) holder).mTitle.setText(news.getTitle());
            ((ItemViewHolder) holder).mDesc.setText(news.getDigest());
            Glide.with(mContext)
                    .load(news.getImgsrc())
                    .error(R.drawable.error)
                    .placeholder(R.drawable.loading)
                    .into(((ItemViewHolder) holder).mNewsImg);

//            (ItemViewHolder)holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)   {
//                    if(mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClick(v,position);
//                    }
//                }
//            });
        }
    }
    @Override
    public int getItemViewType(int position) {
        return ITEM_VIEW;
    }

    @Override
    public int getItemCount() {
     return  mData.size();
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public TextView mDesc;
        public ImageView mNewsImg;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mDesc = (TextView) v.findViewById(R.id.tvDesc);
            mNewsImg = (ImageView) v.findViewById(R.id.ivNews);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

}

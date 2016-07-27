package com.news.ye.newsdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.Meizi;
import com.news.ye.newsdemo.util.DateFormatUtil;
import com.news.ye.newsdemo.widget.RatioImageView;

import java.util.List;

/**
 * Created by YU on 2016/6/16.
 */
public class MeiziAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Meizi> mList;
    private Context mContext;
    private View footer;
    private OnItemClickListener mOnItemClickListener;

    //普通ITEM
    private static final int ITEM_VIEW=1;
    //FOOT ITEM
    private static final int FOOT_VIEW=2;

    public MeiziAdapter(List<Meizi> mList,Context mContext){
        this.mList=mList;
        this.mContext=mContext;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position,View iv);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
           this.mOnItemClickListener=onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, parent, false);
        return new ItemViewHolder(view);
//        if (viewType == ITEM_VIEW) {
//            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, parent, false);
//            return new ItemViewHolder(view);
//        } else if (viewType == FOOT_VIEW) {
//            footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.instance_load_more_layout, parent, false);
//            return new FootViewHolder(footer);
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            Meizi meizi=mList.get(position);
            if (position % 2 == 0) {
                ((ItemViewHolder)holder).iv.setImageRatio(0.7f);
            } else {
                ((ItemViewHolder)holder).iv.setImageRatio(0.6f);
            }
            ((ItemViewHolder)holder).tv_time.setText(DateFormatUtil.formatDate(meizi.getCreatedAt()));

            Glide.with(mContext)
                    .load(meizi.getUrl())
                    .error(R.drawable.error)
                    .placeholder(R.drawable.loading)
                    .into(((ItemViewHolder)holder).iv);

        }
//        else if(holder instanceof FootViewHolder){
//
//        }


    }
//    @Override
//    public int getItemViewType(int position) {
//        if (position + 1 == getItemCount()) {
//            return FOOT_VIEW;
//        } else {
//            return ITEM_VIEW;
//        }
//    }

    public Meizi getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

//    @Override
//    public int getItemCount() {
//        return mList.size()+1;
//    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        RatioImageView iv;
        TextView  tv_time;
        public ItemViewHolder(View itemView) {
            super(itemView);
            iv=(RatioImageView)itemView.findViewById(R.id.ivImage);
            tv_time=(TextView)itemView.findViewById(R.id.time_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(v,getPosition(),iv);
                    }
                }
            });
        }
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;
        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv=(TextView)view.findViewById(R.id.foot_view_item_tv);
        }
    }

    public void setGoneFooter(){
        footer.setVisibility(View.GONE);
    }
    public void setVisibleFooter(){
        footer.setVisibility(View.VISIBLE);
    }

   //瀑布流上拉加载更多时候用到的方法下列3个
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)){
            handleLayoutIfStaggeredGridLayout(holder,holder.getLayoutPosition());
        }
    }
    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (position == mList.size()) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }
}

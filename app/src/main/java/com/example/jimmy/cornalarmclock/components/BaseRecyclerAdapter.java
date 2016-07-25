package com.example.jimmy.cornalarmclock.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.jimmy.cornalarmclock.widget.RecycleViewWithHeaderAndFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 15/12/744.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    public List<T> listData = new ArrayList<>();

    protected Context mContext;
    protected RecycleViewWithHeaderAndFooter mRecyclerView;

    public BaseRecyclerAdapter(Context context, RecycleViewWithHeaderAndFooter recyclerView) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
    }

    public BaseRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<T> getListData() {
        return listData;
    }

    public int getItemPosition(int pos) {
        return mRecyclerView.getItemPosition(pos);
    }

    public T getItem(int position) {
        return listData.get(position);
    }

    public void putData(List<T> datas) {
        if (datas == null) {
            return;
        }

        listData.clear();
        listData.addAll(datas);
        notifyDataSetChanged();
    }

    public void clear() {
        listData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRecyclerView != null) {
            return mRecyclerView.getItemCount(listData.size());
        }
        return listData.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (mRecyclerView != null) {
            viewHolder = mRecyclerView.onCreateViewHolder(viewType);
        }
        if (viewHolder == null) {
            return createHolder(parent, viewType);
        } else {
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mRecyclerView != null) {
            if (!(holder instanceof RecycleViewWithHeaderAndFooter.VHHeader) && !(holder instanceof RecycleViewWithHeaderAndFooter.VHFooter)) {
                bindHolder(holder, mRecyclerView.getItemPosition(position));
            }
        } else {
            bindHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mRecyclerView != null) {
            return mRecyclerView.getItemViewType(position);
        }
        return -1000;
    }

    /**
     * 创建通用item的ViewHolder
     */
    public abstract RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType);

    /**
     * 给ViewHolder中的View绑定信息
     */
    public abstract void bindHolder(RecyclerView.ViewHolder holder, int position);

}

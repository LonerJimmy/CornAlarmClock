package com.example.jimmy.cornalarmclock.ui.alarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jimmy.cornalarmclock.components.BaseRecyclerAdapter;
import com.example.jimmy.cornalarmclock.model.Alarm;
import com.example.jimmy.cornalarmclock.ui.alarm.viewholder.AlarmViewHolder;
import com.example.jimmy.cornalarmclock.ui.alarm.viewholder.MViewHolder;
import com.example.jimmy.cornalarmclock.widget.RecycleViewWithHeaderAndFooter;

import java.util.List;


/**
 * Created by caolicheng on 15/8/5.
 */
public class BaseAlarmAdapter extends BaseRecyclerAdapter<Alarm> {
    private LayoutInflater layoutInflater;

    public BaseAlarmAdapter(Context context, RecycleViewWithHeaderAndFooter recycleViewWithHeaderAndFooter) {
        super(context, recycleViewWithHeaderAndFooter);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new AlarmViewHolder(layoutInflater, parent);
    }

    @Override
    public void bindHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MViewHolder) {
            ((MViewHolder) holder).bindView(getItem(position));
        }
    }

    public void notifyDataSetChanged(List<Alarm> orderlist) {
        putData(orderlist);
    }

}

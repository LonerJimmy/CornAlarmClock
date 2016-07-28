package com.example.jimmy.cornalarmclock.ui.alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.model.Date;

import java.util.List;

/**
 * Created by Jimmy on 16/7/28.
 */
public class GridViewAdapter extends BaseAdapter {

    private List<Date> list;
    private Context context;
    private OnGridClickListener onGridClickListener;

    public GridViewAdapter(Context context, List<Date> list, OnGridClickListener onGridClickListener) {
        this.list = list;
        this.context = context;
        this.onGridClickListener = onGridClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Date getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View ll = LayoutInflater.from(context).inflate(R.layout.item_date_select, null);
        final TextView textView = (TextView) ll.findViewById(R.id.txt_date);
        textView.setText(getItem(position).getDate());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(position).getIsSelected()) {
                    list.get(position).setSelected(false);
                    textView.setBackgroundResource(R.drawable.bg_circle_normal);
                    textView.setTextColor(context.getResources().getColor(R.color.grey_fetch_deep));
                } else {
                    list.get(position).setSelected(true);
                    textView.setBackgroundResource(R.drawable.bg_circle_selected);
                    textView.setTextColor(context.getResources().getColor(R.color.white));
                }
                onGridClickListener.onClick(list.get(position));
            }
        });
        return ll;
    }

    public interface OnGridClickListener {
        void onClick(Date mapType);
    }
}

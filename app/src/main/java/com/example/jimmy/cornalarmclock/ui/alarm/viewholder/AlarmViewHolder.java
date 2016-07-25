package com.example.jimmy.cornalarmclock.ui.alarm.viewholder;

import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseRecyclerViewHolder;
import com.example.jimmy.cornalarmclock.model.Alarm;

import butterknife.Bind;

/**
 * Created by Jimmy on 16/7/25.
 */
public class AlarmViewHolder extends BaseRecyclerViewHolder implements MViewHolder {

    @Bind(R.id.txt_time)
    TextView timeView;
    @Bind(R.id.txt_date)
    TextView dateView;
    @Bind(R.id.txt_title)
    TextView titleView;
    @Bind(R.id.switch_alarm)
    SwitchCompat mSwitch;

    public LayoutInflater inflater;

    public AlarmViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_alarm, parent, false));
        this.inflater = inflater;
    }

    @Override
    public void bindView(Alarm alarm) {
        timeView.setText(Long.toString(alarm.getTime()));
        titleView.setText(alarm.getTitle());
    }
}

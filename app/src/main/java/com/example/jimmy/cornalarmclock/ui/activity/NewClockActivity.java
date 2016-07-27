package com.example.jimmy.cornalarmclock.ui.activity;

import android.os.Bundle;
import android.widget.TimePicker;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseListActivity;
import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.widget.MultiStateView;

/**
 * Created by Jimmy on 16/7/27.
 */
public class NewClockActivity extends BaseListActivity {

    private TimePicker timePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(new Title("新建闹钟", R.drawable.ic_ring_close, R.drawable.ic_ring_mark));
        multiContainer.setState(MultiStateView.STATE_CONTENT);
        setListener();

        timePicker = (TimePicker) content.findViewById(R.id.time_picker);
    }

    @Override
    protected int getContent() {
        return R.layout.activity_new_alarm;
    }

    @Override
    public void clickLeft() {
        this.finish();
    }

    @Override
    public void clickRight() {

    }
}

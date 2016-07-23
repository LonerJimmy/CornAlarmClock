package com.example.jimmy.cornalarmclock.ui.alarm;

import android.os.Bundle;
import android.view.View;

import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.ui.home.BaseCornFragment;

/**
 * Created by Jimmy on 16/7/7.
 */
public class AlarmFragment extends BaseCornFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(new Title("玉米闹钟", 0, 0));
    }

    @Override
    protected int getContent() {
        return 0;
    }

    @Override
    protected void clickLeft() {

    }

    @Override
    protected void clickRight() {

    }

}

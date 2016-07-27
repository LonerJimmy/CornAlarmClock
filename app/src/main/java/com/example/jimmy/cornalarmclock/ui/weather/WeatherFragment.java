package com.example.jimmy.cornalarmclock.ui.weather;

import android.os.Bundle;
import android.view.View;

import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.components.BaseCornFragment;

/**
 * Created by Jimmy on 16/7/23.
 */
public class WeatherFragment extends BaseCornFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(new Title("玉米天气", 0, 0));
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

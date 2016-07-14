package com.example.jimmy.cornalarmclock.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseFragment;

/**
 * Created by Jimmy on 16/7/7.
 */
public class AlarmFragment extends BaseFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_alarm, container, false);
        return view;
    }

    @Override
    public void fetchData() {

    }
}

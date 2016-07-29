package com.example.jimmy.cornalarmclock.ui.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseCornFragment;
import com.example.jimmy.cornalarmclock.model.AlarmClock;
import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.ui.activity.NewClockActivity;
import com.example.jimmy.cornalarmclock.util.DbManager;
import com.example.jimmy.cornalarmclock.widget.MultiStateView;
import com.example.jimmy.cornalarmclock.widget.RecycleViewWithHeaderAndFooter;
import com.example.jimmy.cornalarmclock.widget.animation.MyRecyclerAnimator;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Jimmy on 16/7/7.
 */
public class AlarmFragment extends BaseCornFragment {

    private RecycleViewWithHeaderAndFooter mRecyclerView;
    private BaseAlarmAdapter alarmAdapter;
    private MultiStateView multiStateView;
    private DbManager dbManager = DbManager.getInstance();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initList();

        updateAlarmList(dbManager.getAlarmInfos());

        setListenr();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAlarmList(dbManager.getAlarmInfos());
    }

    private void updateAlarmList(List<AlarmClock> list) {
        if (list == null) {
            return;
        }

        if (list.isEmpty()) {
            multiStateView.setState(MultiStateView.STATE_EMPTY).setTitle("请添加右上角+按钮来添加闹钟");
        } else {
            multiStateView.setState(MultiStateView.STATE_CONTENT);
        }

        alarmAdapter.notifyDataSetChanged(list);
    }

    private void initView() {
        setTitle(new Title("玉米闹钟", R.drawable.ic_action_edit, R.drawable.ic_action_new));
        mRecyclerView = ButterKnife.findById(content, R.id.alarm_recycler_view);
        multiStateView = ButterKnife.findById(content, R.id.fragment_multi_state_view);
        multiStateView.setState(MultiStateView.STATE_LOADING);
    }

    private void initList() {
        alarmAdapter = new BaseAlarmAdapter(getActivity(), mRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(alarmAdapter);
        mRecyclerView.setItemAnimator(new MyRecyclerAnimator());
    }

    @Override
    protected int getContent() {
        return R.layout.fragment_alarm;
    }

    @Override
    protected void clickLeft() {
        DbManager.getInstance().clearAll();
        updateAlarmList(dbManager.getAlarmInfos());
    }

    @Override
    protected void clickRight() {
        Intent intent = new Intent();
        intent.putExtra("count", alarmAdapter.getItemCount());
        intent.setClass(getActivity(), NewClockActivity.class);
        startActivity(intent);
    }

}

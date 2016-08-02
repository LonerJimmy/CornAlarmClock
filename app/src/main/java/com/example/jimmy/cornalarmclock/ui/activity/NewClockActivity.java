package com.example.jimmy.cornalarmclock.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.broadcast.AlarmClockBroadcast;
import com.example.jimmy.cornalarmclock.components.BaseListActivity;
import com.example.jimmy.cornalarmclock.constant.AlarmConstants;
import com.example.jimmy.cornalarmclock.model.Date;
import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.ui.alarm.GridViewAdapter;
import com.example.jimmy.cornalarmclock.util.DbManager;
import com.example.jimmy.cornalarmclock.util.StringUtil;
import com.example.jimmy.cornalarmclock.widget.MultiStateView;
import com.example.jimmy.cornalarmclock.widget.PickerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jimmy on 16/7/27.
 */
public class NewClockActivity extends BaseListActivity implements GridViewAdapter.OnGridClickListener {

    private PickerView hourPicker;
    private PickerView minutePicker;
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private SeekBar volumeSeekBar;
    private TextView selectDateText;
    private TextView txtRing;
    private LinearLayout llRing;
    private SwitchCompat vibrateSwitch;

    private int count;

    private List<Date> result = new ArrayList<>();
    private List<Date> list = new ArrayList<Date>() {
        {
            add(new Date(7, "周日", false));
            add(new Date(1, "周一", false));
            add(new Date(2, "周二", false));
            add(new Date(3, "周三", false));
            add(new Date(4, "周四", false));
            add(new Date(5, "周五", false));
            add(new Date(6, "周六", false));
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //其它初始化
        initOther();
        //初始化时间选择
        initTimeSelect();
        //初始化重复
        initRepeat();
        //初始化音量
        initVolume();
        // 初始化铃声
        initRing();
        //初始化振动
        initVibrate();

    }

    private void initOther() {
        count = getIntent().getIntExtra("count", 0);
//        if (DbManager.getInstance().getAlarm(Integer.toString(count - 1)) != null) {
//            alarmClock = DbManager.getInstance().getAlarm(Integer.toString(count));
//        }
        alarmClock.setId(Integer.toString(count));
        alarmClock.setOnOff(true);
        alarmClock.setTag(AlarmConstants.TAG_DEFAULT);

        setTitle(new Title("新建闹钟", R.drawable.ic_action_cancel, R.drawable.ic_action_accept));
        multiContainer.setState(MultiStateView.STATE_CONTENT);
    }

    @Override
    public void onResume() {
        super.onResume();
        setListener();
    }

    private void initVibrate() {
        vibrateSwitch = (SwitchCompat) content.findViewById(R.id.switch_vibrate);
        alarmClock.setVibrate(AlarmConstants.VIBRATE_DEFAULT);
        vibrateSwitch.setSelected(AlarmConstants.VIBRATE_DEFAULT);
    }

    private void initRing() {
        txtRing = (TextView) content.findViewById(R.id.txt_ring);
        llRing = (LinearLayout) content.findViewById(R.id.ll_ring);
        txtRing.setText(AlarmConstants.RING_DEFAULT);
        // 初始化闹钟实例的铃声名
        alarmClock.setRingName(AlarmConstants.RING_DEFAULT);

        llRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmClock.setRingName(AlarmConstants.RING_DEFAULT);
            }
        });
    }

    private void initTimeSelect() {
        // 下次响铃提示
        hourPicker = (PickerView) content.findViewById(R.id.pv_hour);
        minutePicker = (PickerView) content.findViewById(R.id.pv_minute);

        List<String> data = new ArrayList<String>();
        List<String> seconds = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            data.add(i < 10 ? "0" + i : "" + i);
        }
        for (int i = 0; i < 60; i++) {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }
        hourPicker.setData(data);
        hourPicker.setOnSelectListener(new PickerView.OnSelectListener() {

            @Override
            public void onSelect(String text) {
                if (text != null) {
                    alarmClock.setHour(Integer.parseInt(text));
                }
            }
        });
        minutePicker.setData(seconds);
        minutePicker.setOnSelectListener(new PickerView.OnSelectListener() {

            @Override
            public void onSelect(String text) {
                alarmClock.setMinute(Integer.parseInt(text));
            }
        });
        hourPicker.setSelected(0);
        alarmClock.setHour(0);
        alarmClock.setMinute(30);

    }

    private void initRepeat() {
        alarmClock.setRepeat(AlarmConstants.REPEAT_DEFAULT);
        gridView = (GridView) content.findViewById(R.id.grid_repeat);
        selectDateText = (TextView) content.findViewById(R.id.txt_repeat);
        gridViewAdapter = new GridViewAdapter(this, list, this);
        gridView.setAdapter(gridViewAdapter);
    }

    private void initVolume() {
        volumeSeekBar = (SeekBar) content.findViewById(R.id.seek_bar);

        volumeSeekBar.setProgress(AlarmConstants.VOLUME_DEFAULT);
        alarmClock.setVolume(AlarmConstants.VOLUME_DEFAULT);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 保存设置的音量
                alarmClock.setVolume(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });
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
        if (!StringUtil.isBlank(editTitle.getText().toString())) {
            alarmClock.setTag(editTitle.getText().toString());
        }
        DbManager.getInstance().saveOrUpdate(alarmClock);

        Intent data = new Intent(this, AlarmClockBroadcast.class);
        setResult(Activity.RESULT_OK, data);
        drawAnimation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
//        switch (requestCode) {
//            // 铃声选择界面返回
//            case REQUEST_RING_SELECT:
//                // 铃声名
//                String name = data.getStringExtra(WeacConstants.RING_NAME);
//                // 铃声地址
//                String url = data.getStringExtra(WeacConstants.RING_URL);
//                // 铃声界面
//                int ringPager = data.getIntExtra(WeacConstants.RING_PAGER, 0);
//
//                mRingDescribe.setText(name);
//
//                mAlarmClock.setRingName(name);
//                mAlarmClock.setRingUrl(url);
//                mAlarmClock.setRingPager(ringPager);
//                break;
//            // 小睡编辑界面返回
//            case REQUEST_NAP_EDIT:
//                // 小睡间隔
//                int napInterval = data.getIntExtra(WeacConstants.NAP_INTERVAL, 10);
//                // 小睡次数
//                int napTimes = data.getIntExtra(WeacConstants.NAP_TIMES, 3);
//                mAlarmClock.setNapInterval(napInterval);
//                mAlarmClock.setNapTimes(napTimes);
//                break;
//        }
    }

    /**
     * 结束新建闹钟界面时开启渐变缩小效果动画
     */
    private void drawAnimation() {
        finish();
        overridePendingTransition(0, R.anim.zoomout);
    }

    @Override
    public void onClick(Date mapType) {
        if (result.contains(mapType)) {
            result.remove(mapType);
        } else {
            result.add(mapType);
        }

        Collections.sort(result, Date.dateComparator);
        selectDateText.setText(StringUtil.getInstance().listDateToString(result));
        alarmClock.setRepeat(StringUtil.getInstance().listDateToString(result));
    }
}

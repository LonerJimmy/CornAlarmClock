package com.example.jimmy.cornalarmclock.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseListActivity;
import com.example.jimmy.cornalarmclock.constant.AlarmConstants;
import com.example.jimmy.cornalarmclock.model.Date;
import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.ui.alarm.GridViewAdapter;
import com.example.jimmy.cornalarmclock.util.DbManager;
import com.example.jimmy.cornalarmclock.util.StringUtil;
import com.example.jimmy.cornalarmclock.widget.MultiStateView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jimmy on 16/7/27.
 */
public class NewClockActivity extends BaseListActivity implements GridViewAdapter.OnGridClickListener {

    private TimePicker timePicker;
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

        count = getIntent().getIntExtra("count", 0);
        if (DbManager.getInstance().getAlarm(Integer.toString(count)) != null) {
            alarmClock = DbManager.getInstance().getAlarm(Integer.toString(count));
        }
        alarmClock.setId(Integer.toString(count + 1));

        setTitle(new Title("新建闹钟", R.drawable.ic_action_cancel, R.drawable.ic_action_accept));
        multiContainer.setState(MultiStateView.STATE_CONTENT);

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
        timePicker = (TimePicker) content.findViewById(R.id.time_picker);

        timePicker.setCurrentHour(AlarmConstants.HOUR_DEFAULT);
        timePicker.setCurrentMinute(AlarmConstants.MINUTE_DEAFULT);

        // 初始化闹钟实例的小时
        alarmClock.setHour(AlarmConstants.HOUR_DEFAULT);
        // 初始化闹钟实例的分钟
        alarmClock.setMinute(AlarmConstants.MINUTE_DEAFULT);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // 保存闹钟实例的小时
                alarmClock.setHour(hourOfDay);
                // 保存闹钟实例的分钟
                alarmClock.setMinute(minute);

            }

        });
    }

    private void initRepeat() {
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
        alarmClock.setTag(editTitle.getText().toString());
        DbManager.getInstance().saveOrUpdate(alarmClock);
        this.finish();
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

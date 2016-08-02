package com.example.jimmy.cornalarmclock.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jimmy.cornalarmclock.model.AlarmClock;
import com.example.jimmy.cornalarmclock.util.AlarmUtil;
import com.example.jimmy.cornalarmclock.util.DbManager;

import java.util.ArrayList;

/**
 * Created by Joe on 2016/1/23.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alarm", "Boot收到广播");
        checkAndStartAlarm(context);
    }

    //开机时检查是否有闹钟需要开启
    private void checkAndStartAlarm(Context context) {
        Log.d("alarm", "开始检查是否有闹钟");
        ArrayList<AlarmClock> list = (ArrayList<AlarmClock>) DbManager.getInstance().getAlarmInfos();

        for (AlarmClock alarmInfo : list) {
            if (alarmInfo.isOnOff()) {
                AlarmUtil.getInstance().turnAlarm(context, alarmInfo);
            }

        }
    }
}

package com.example.jimmy.cornalarmclock.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.jimmy.cornalarmclock.broadcast.AlarmReciver;
import com.example.jimmy.cornalarmclock.constant.AlarmConstants;
import com.example.jimmy.cornalarmclock.model.AlarmClock;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jimmy on 16/7/29.
 */
public class AlarmUtil {

    private static AlarmUtil ourInstance = new AlarmUtil();

    public static AlarmUtil getInstance() {
        return ourInstance;
    }

    private AlarmUtil() {
    }

    public void turnAlarm(Context context, AlarmClock alarmInfo) {
        if (alarmInfo == null) {
            Log.d("alarm", "传入AlarmInfo不为空");
        }

        AlarmManager mAlamManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReciver.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AlarmConstants.ALARM_CLOCK, alarmInfo);
        intent.putExtras(bundle);
        intent.setAction(AlarmConstants.RECEIVER_ACTION);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        //每个闹钟不同的pi
        PendingIntent pi = PendingIntent.getBroadcast(context, Integer.parseInt(alarmInfo.getId()), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmInfo.isOnOff()) {
            startAlarm(mAlamManager, alarmInfo, pi);
        } else {
            cancelAlarm(intent, context);
        }
    }

    private void cancelAlarm(Intent intent, Context context) {
        Log.d("alarm", "取消闹钟");
        intent.putExtra("cancel", true);
        context.sendBroadcast(intent);
    }

    public void startAlarm(AlarmManager mAlamManager, AlarmClock alarmInfo, PendingIntent pi) {
        Log.d("alarm", "启动闹钟");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, alarmInfo.getHour());
        c.set(Calendar.MINUTE, alarmInfo.getMinute());
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        //  Log.d("alarm", "当前系统版本" + Build.VERSION.SDK_INT);
        if (c.getTimeInMillis() < System.currentTimeMillis()) {
            if (Build.VERSION.SDK_INT >= 19) {
                mAlamManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis() + 24 * 60 * 60 * 1000, pi);
            } else {
                mAlamManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis() + 24 * 60 * 60 * 1000, pi);
            }
        } else {
            if (Build.VERSION.SDK_INT >= 19) {
                Log.d("alarm", "执行定时任务");
                Date date = c.getTime();
                Log.d("alarm", "定时的时间是" + date.toString());
                mAlamManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
            } else {
                mAlamManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
            }
        }

    }

}

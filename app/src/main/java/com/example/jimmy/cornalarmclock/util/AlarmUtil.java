package com.example.jimmy.cornalarmclock.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

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

    /**
     * 开启倒计时
     *
     * @param context    context
     * @param timeRemain 剩余时间
     */
    public static void startAlarmTimer(Context context, long timeRemain) {
//        Intent intent = new Intent(context, TimerOnTimeActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(context,
//                1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) context
//                .getSystemService(Context.ALARM_SERVICE);
//        long countdownTime = timeRemain + SystemClock.elapsedRealtime();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, countdownTime, pi);
//        } else {
//            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, countdownTime, pi);
//        }
    }
}

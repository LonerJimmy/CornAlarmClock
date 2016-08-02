package com.example.jimmy.cornalarmclock.broadcast;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import com.example.jimmy.cornalarmclock.constant.AlarmConstants;
import com.example.jimmy.cornalarmclock.model.AlarmClock;
import com.example.jimmy.cornalarmclock.util.AlarmUtil;
import com.example.jimmy.cornalarmclock.util.DbManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Joe on 2016/1/11.
 */
public class AlarmReciver extends BroadcastReceiver {

    private Context context;
    private AlarmManager alarmManager;
    private List<Integer> dayOfWeek = new ArrayList<>();
    private AlarmClock currentAlarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Log.d("alarm", "收到广播");
        Bundle bundle = intent.getExtras();
        currentAlarm = (AlarmClock) bundle.getParcelable(AlarmConstants.ALARM_CLOCK);

        dayOfWeek = AlarmClock.getWeekDay(currentAlarm);

        if (intent.getBooleanExtra("cancel", false)) {
            cancelAlarm(intent);
            return;
        }
        if (dayOfWeek.size() == 0) {
            wakePhoneAndUnlock();
            ringAlarm();
            currentAlarm.setOnOff(false);
            DbManager.getInstance().saveOrUpdate(currentAlarm);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            for (int i = 0; i < dayOfWeek.size(); i++) {
                if (currentDay == dayOfWeek.get(i)) {
                    wakePhoneAndUnlock();
                    ringAlarm();
                }
            }
            runAlarmAgain(intent);
        }
    }


    private void cancelAlarm(Intent intent) {
        Log.d("alarm", "取消闹钟");
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(context, Integer.parseInt(currentAlarm.getId()), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pi);
    }

    private void runAlarmAgain(Intent intent) {

        //未完成
        Log.d("alarm", "再次启动闹钟");
        AlarmUtil.getInstance().turnAlarm(context, currentAlarm);
    }

    //点亮屏幕并解锁
    private void wakePhoneAndUnlock() {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, "WakeLock");
        mWakelock.acquire();
        KeyguardManager mManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock mKeyguardLock = mManager.newKeyguardLock("Lock");
        mKeyguardLock.disableKeyguard();
        mWakelock.release();
    }

    private void ringAlarm() {
//        if (PrefUtils.getBoolean(context, ConsUtils.SHOULD_WETHER_CLOSE, false)) {
//            //如果用户关闭了天气 不再弹出Activity;
//        } else {
//            //打开天气提示
//            Intent it = new Intent(context, WakeUpActivity.class);
//            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(it);
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
    }

}

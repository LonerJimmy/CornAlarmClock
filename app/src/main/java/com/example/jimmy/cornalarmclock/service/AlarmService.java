/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.jimmy.cornalarmclock.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.jimmy.cornalarmclock.model.AlarmClock;
import com.example.jimmy.cornalarmclock.util.AlarmUtil;
import com.example.jimmy.cornalarmclock.util.DbManager;

import java.util.List;

public class AlarmService extends Service {

//    private static final String LOG_TAG = "AlarmService";

//    /**
//     * 定时唤醒的时间间隔，5分钟
//     */
//    private final static int ALARM_INTERVAL = 5 * 60 * 1000;
//    private final static int WAKE_REQUEST_CODE = 6666;

    private final int GRAY_SERVICE_ID = -1001;

    @Override
    public void onCreate() {
        super.onCreate();
//        Daemon.run(AlarmService.this, AlarmService.class, Daemon.INTERVAL_ONE_MINUTE);
        startTimeTask();
//        grayGuard();

        // Notification notification = new Notification();
        // startForeground(-1, notification);
    }

    private void startTimeTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AlarmClock> list = DbManager.getInstance().getAlarmInfos();
                for (AlarmClock alarmClock : list) {
                    // 当闹钟为开时刷新开启闹钟
                    if (alarmClock.isOnOff()) {
                        AlarmUtil.startAlarmClock(AlarmService.this, alarmClock);
                    }
                }

//                SharedPreferences preferences = getSharedPreferences(
//                        AlarmConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
//                // 倒计时时间
//                long countdown = preferences.getLong(AlarmConstants.COUNTDOWN_TIME, 0);
//                boolean isStop = preferences.getBoolean(AlarmConstants.IS_STOP, false);
//                if (countdown != 0 && !isStop) {
//                    long now = SystemClock.elapsedRealtime();
//                    long remainTime = countdown - now;
//                    if (remainTime > 0 && (remainTime / 1000 / 60) < 60) {
//                        AlarmUtil.startAlarmTimer(AlarmService.this, remainTime);
//                    }
//                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, AlarmService.class));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

//    private void grayGuard() {
//        if (Build.VERSION.SDK_INT < 18) {
//            startForeground(GRAY_SERVICE_ID, new Notification());//API < 18 ，此方法能有效隐藏Notification上的图标
//        } else {
//            Intent innerIntent = new Intent(this, DaemonInnerService.class);
//            startService(innerIntent);
//            startForeground(GRAY_SERVICE_ID, new Notification());
//        }
//
//        //发送唤醒广播来促使挂掉的UI进程重新启动起来
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent alarmIntent = new Intent();
//        alarmIntent.setAction(WakeReceiver.GRAY_WAKE_ACTION);
//        PendingIntent operation = PendingIntent.getBroadcast(this, WAKE_REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), ALARM_INTERVAL, operation);
//    }

    /**
     * 给 API >= 18 的平台上用的灰色保活手段
     */
    public class DaemonInnerService extends Service {

        @Override
        public void onCreate() {
            super.onCreate();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            //stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    }
}

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
package com.example.jimmy.cornalarmclock.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jimmy.cornalarmclock.constant.AlarmConstants;
import com.example.jimmy.cornalarmclock.model.AlarmClock;
import com.example.jimmy.cornalarmclock.util.AlarmUtil;
import com.example.jimmy.cornalarmclock.util.DbManager;

/**
 * 闹钟响起广播
 *
 */
public class AlarmClockBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmClock alarmClock = intent
                .getParcelableExtra(AlarmConstants.ALARM_CLOCK);
        // 单次响铃
        if (alarmClock.getWeeks() == null) {
            alarmClock.setOnOff(false);
            DbManager.getInstance().saveOrUpdate(alarmClock);

            Intent i = new Intent("com.kaku.weac.AlarmClockOff");
            context.sendBroadcast(i);
        } else {
            // 重复周期闹钟
            AlarmUtil.startAlarmClock(context, alarmClock);
        }
    }
}

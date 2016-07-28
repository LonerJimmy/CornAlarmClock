package com.example.jimmy.cornalarmclock.util;

import android.text.TextUtils;

import com.example.jimmy.cornalarmclock.context.AppApplication;
import com.example.jimmy.cornalarmclock.model.AlarmClock;

import java.util.List;

import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;
import xiaofei.library.datastorage.util.Condition;

/**
 * Created by Jimmy on 16/4/1.
 */
public class DbManager {

    private static volatile DbManager dbUtil;
    private static IDataStorage iDataStorage;

    public DbManager() {
        iDataStorage = DataStorageFactory.getInstance(AppApplication.getInstance(), DataStorageFactory.TYPE_DATABASE);
    }

    public static DbManager getInstance() {
        if (dbUtil == null) {
            synchronized (DbManager.class) {
                if (dbUtil == null) {
                    dbUtil = new DbManager();
                }
            }
        }
        return dbUtil;
    }

    public AlarmClock getAlarm(final String id) {
        List<AlarmClock> orderInfos = iDataStorage.load(AlarmClock.class, new Condition<AlarmClock>() {
            @Override
            public boolean satisfy(AlarmClock o) {
                return TextUtils.equals(id, o.getId());
            }
        });
        if (orderInfos != null && orderInfos.size() > 0) {
            return orderInfos.get(0);
        }
        return null;
    }

    public List<AlarmClock> getAlarmInfos() {
        return iDataStorage.loadAll(AlarmClock.class);
    }

    public void saveOrUpdate(List<AlarmClock> alarmClocks) {
        iDataStorage.storeOrUpdate(alarmClocks);
    }

    public void clearOrder(AlarmClock alarmClock) {
        iDataStorage.delete(alarmClock);
    }

    public void clearAll() {
        iDataStorage.deleteAll(AlarmClock.class);
    }

    public void saveOrUpdate(AlarmClock alarmClock) {
        iDataStorage.storeOrUpdate(alarmClock);
    }

}

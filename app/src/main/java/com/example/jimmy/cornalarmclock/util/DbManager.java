package com.example.jimmy.cornalarmclock.util;

import com.example.jimmy.cornalarmclock.context.AppApplication;
import com.example.jimmy.cornalarmclock.model.Alarm;

import java.util.List;

import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;

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

    public List<Alarm> getAlarmInfos() {
        return iDataStorage.loadAll(Alarm.class);
    }

    public void saveOrUpdate(List<Alarm> newOrders) {
        iDataStorage.storeOrUpdate(newOrders);
    }

    public void clearOrder(Alarm order) {
        iDataStorage.delete(order);
    }

    public void saveOrUpdate(Alarm order) {
        iDataStorage.storeOrUpdate(order);
    }

}

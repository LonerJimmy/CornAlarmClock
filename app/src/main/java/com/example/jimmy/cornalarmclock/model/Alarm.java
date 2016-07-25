package com.example.jimmy.cornalarmclock.model;

import java.util.ArrayList;
import java.util.List;

import xiaofei.library.datastorage.annotation.ObjectId;

/**
 * Created by Jimmy on 16/7/25.
 */
public class Alarm {

    @ObjectId
    private String createTime;

    private long time;
    private List<String> date;
    private boolean isOn;
    private String title;

    private Alarm() {
        createTime = "";
        time = 0;
        date = new ArrayList<>();
        isOn = false;
        title = "";
    }

    public void setCreateTime(String id) {
        this.createTime = id;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public boolean getOn() {
        return isOn;
    }

    public List<String> getDate() {
        return date;
    }
}

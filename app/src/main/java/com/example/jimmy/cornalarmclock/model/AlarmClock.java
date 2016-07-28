package com.example.jimmy.cornalarmclock.model;

import android.os.Parcel;
import android.os.Parcelable;

import xiaofei.library.datastorage.annotation.ClassId;
import xiaofei.library.datastorage.annotation.ObjectId;

/**
 * Created by Jimmy on 16/7/28.
 */
@ClassId("AlarmClock")
public class AlarmClock implements Parcelable {

    /**
     * 闹钟id
     */
    @ObjectId
    private String id;

    /**
     * 小时
     */
    private int hour;

    /**
     * 分钟
     */
    private int minute;

    /**
     * 重复
     */
    private String repeat;

    /**
     * 周期
     */
    private String weeks;

    /**
     * 标签
     */
    private String tag;

    /**
     * 铃声名
     */
    private String ringName;

    /**
     * 铃声地址
     */
    private String ringUrl;

    /**
     * 铃声选择标记界面
     */
    private int ringPager;

    /**
     * 音量
     */
    private int volume;

    /**
     * 振动
     */
    private boolean vibrate;


    /**
     * 开关
     */
    private boolean onOff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRingName() {
        return ringName;
    }

    public void setRingName(String ringName) {
        this.ringName = ringName;
    }

    public String getRingUrl() {
        return ringUrl;
    }

    public int getRingPager() {
        return ringPager;
    }

    public void setRingPager(int ringPager) {
        this.ringPager = ringPager;
    }

    public void setRingUrl(String ringUrl) {
        this.ringUrl = ringUrl;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    public AlarmClock() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.hour);
        dest.writeInt(this.minute);
        dest.writeString(this.repeat);
        dest.writeString(this.weeks);
        dest.writeString(this.tag);
        dest.writeString(this.ringName);
        dest.writeString(this.ringUrl);
        dest.writeInt(this.ringPager);
        dest.writeInt(this.volume);
        dest.writeByte(this.vibrate ? (byte) 1 : (byte) 0);
        dest.writeByte(this.onOff ? (byte) 1 : (byte) 0);
    }

    protected AlarmClock(Parcel in) {
        this.id = in.readString();
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.repeat = in.readString();
        this.weeks = in.readString();
        this.tag = in.readString();
        this.ringName = in.readString();
        this.ringUrl = in.readString();
        this.ringPager = in.readInt();
        this.volume = in.readInt();
        this.vibrate = in.readByte() != 0;
        this.onOff = in.readByte() != 0;
    }

    public static final Creator<AlarmClock> CREATOR = new Creator<AlarmClock>() {
        @Override
        public AlarmClock createFromParcel(Parcel source) {
            return new AlarmClock(source);
        }

        @Override
        public AlarmClock[] newArray(int size) {
            return new AlarmClock[size];
        }
    };
}

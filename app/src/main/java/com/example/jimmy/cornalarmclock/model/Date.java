package com.example.jimmy.cornalarmclock.model;

import java.util.Comparator;

/**
 * Created by Jimmy on 16/7/28.
 */
public class Date {
    private int id;
    private String date;
    private boolean isSelected;

    public Date() {
        date = "";
        isSelected = false;
    }

    public Date(int id, String date, boolean isSelected) {
        this.id = id;
        this.date = date;
        this.isSelected = isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public static Comparator<Date> dateComparator = new Comparator<Date>() {

        @Override
        public int compare(Date lhs, Date rhs) {

            int t1 = lhs.getId();
            int t2 = rhs.getId();

            if (t1 < t2) {
                return -1;
            } else if (t1 > t2) {
                return 1;
            } else {
                return 0;
            }
        }
    };
}

package com.example.jimmy.cornalarmclock.util;

import com.example.jimmy.cornalarmclock.model.Date;

import java.util.List;

/**
 * Created by Jimmy on 16/7/28.
 */
public class StringUtil {
    private static StringUtil ourInstance = new StringUtil();

    public static StringUtil getInstance() {
        return ourInstance;
    }

    private StringUtil() {
    }

    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public static String listDateToString(List<Date> dateList) {
        if (dateList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (Date date : dateList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(date.getDate());
        }
        return result.toString();
    }
}

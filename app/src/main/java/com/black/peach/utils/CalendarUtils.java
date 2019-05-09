package com.black.peach.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    public static int getWeekByDate(String time) {
        Calendar cal = Calendar.getInstance();
        int week;
        int temp = -1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = dateFormat.parse(time);
            cal.setTime(date);
            temp = cal.get(Calendar.DAY_OF_WEEK);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        switch (temp) {
            case Calendar.MONDAY:
                week = 1;
                break;
            case Calendar.TUESDAY:
                week = 2;
                break;
            case Calendar.WEDNESDAY:
                week = 3;
                break;
            case Calendar.THURSDAY:
                week = 4;
                break;
            case Calendar.FRIDAY:
                week = 5;
                break;
            case Calendar.SATURDAY:
                week = 6;
                break;
            case Calendar.SUNDAY:
                week = 7;
                break;
            default:
                week = -1;
        }

        return week;
    }
}

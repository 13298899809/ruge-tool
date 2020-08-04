package com.ruge.core;

import com.ruge.core.constant.DateConstant;
import com.ruge.core.enums.OrderEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName DateTool
 * @date 2020.07.31 16:00
 */
public class DateTool {

    /**
     * @param date 当前时间
     * @param day  变动的天数，参数小于0为减法
     * @return 对当前时间做天数的操作
     */
    public static long addDay(long date, int day) {
        return date + day * DateConstant.DATE_TIME_DAY;
    }

    /**
     * @param date 当前时间
     * @param hour 变动的小时，参数小于0为减法
     * @return 对当前时间做小时的操作
     */
    public static long addHour(long date, int hour) {
        return date + hour * DateConstant.DATE_TIME_HOUR;
    }

    /**
     * @param date    当前时间
     * @param minutes 变动的分钟，参数小于0为减法
     * @return 对当前时间做分钟的操作
     */
    public static long addMinutes(long date, int minutes) {
        return date + minutes * DateConstant.DATE_TIME_MIN;
    }


    /**
     * @param startTime     开始日期(时间戳)
     * @param endTime       结束日期(时间戳)
     * @param startStopTime 最早开始时间(时间戳)
     * @param endStopTime   最晚结束时间(时间戳)
     * @param orderEnum     orderEnum {@link OrderEnum} 默认ASC升序
     * @return 获取两个日期之间的所有日期
     */
    public static List<String> getDays(long startTime, long endTime, Long startStopTime, Long endStopTime, OrderEnum orderEnum) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        if (null != startStopTime) {
            start = new Date(startStopTime);
        } else {
            start = new Date(startTime);
        }
        Date end;
        if (null != endStopTime) {
            end = new Date(endStopTime);
        } else {
            end = new Date(endTime);
        }
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
        while (tempStart.before(tempEnd)) {
            days.add(dateFormat.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        if (orderEnum == OrderEnum.DESC) {
            CollectionTool.reverse(days);
        }
        return days;
    }

    /**
     * @param date 当前时间的时间戳
     * @return 获取日初的时间戳
     */
    public static long getDayBegin(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        return calendar.getTime().getTime();

    }

    /**
     * @param date 当前时间的时间戳
     * @return 获取月日初的时间戳
     */
    public static long getDayEnd(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        return calendar.getTime().getTime();

    }

    /**
     * @param date 当前时间的时间戳
     * @return 时间格式化 2020-8-4
     */
    public static String getDateInstance(long date) {
        return DateFormat.getDateInstance().format(date);
    }
}

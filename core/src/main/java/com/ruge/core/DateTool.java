package com.ruge.core;

import com.ruge.core.constant.DateConstant;
import com.ruge.core.enums.OrderEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName DateTool
 * @date 2020.07.31 16:00
 */
public class DateTool {

    /**
     * @param date 当前时间
     * @param year 变动的年数，参数小于0为减法
     * @return 对当前时间做天数的操作
     */
    public static long addYear(long date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date)); // 设置为当前时间
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        return calendar.getTime().getTime();
    }

    /**
     * @param date  当前时间
     * @param month 变动的月数，参数小于0为减法
     * @return 对当前时间做天数的操作
     */
    public static long addMonth(long date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date)); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        return calendar.getTime().getTime();
    }

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
     * @param startTime 开始日期(时间戳)
     * @param endTime   结束日期(时间戳)
     * @return 获取两个日期之间的所有日期
     */
    public static List<String> getDays(long startTime, long endTime) {
        return getDays(startTime, endTime, null, null, OrderEnum.ASC);
    }

    /**
     * @param startTime 开始日期(时间戳)
     * @param endTime   结束日期(时间戳)
     * @param orderEnum orderEnum {@link OrderEnum} 默认ASC升序
     * @return 获取两个日期之间的所有日期
     */
    public static List<String> getDays(long startTime, long endTime, OrderEnum orderEnum) {
        return getDays(startTime, endTime, null, null, orderEnum);
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
        List<String> days = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        if (null != startStopTime && startStopTime > startTime) {
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
     * @param startTime     开始日期(时间戳)
     * @param endTime       结束日期(时间戳)
     * @param startStopTime 最早开始时间(时间戳)
     * @param endStopTime   最晚结束时间(时间戳)
     * @param orderEnum     orderEnum {@link OrderEnum} 默认ASC升序
     * @return 获取两个日期之间的所有日期
     */
    public static List<String> getMonths(long startTime, long endTime, Long startStopTime, Long endStopTime, OrderEnum orderEnum) {
        // 返回的日期集合
        List<String> months = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date start;
        if (null != startStopTime && startStopTime > startTime) {
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
        tempEnd.add(Calendar.MONTH, +1);// 日期加1(包含结束)
        while (tempStart.before(tempEnd)) {
            months.add(dateFormat.format(tempStart.getTime()));
            tempStart.add(Calendar.MONTH, 1);
        }
        if (orderEnum == OrderEnum.DESC) {
            CollectionTool.reverse(months);
        }
        return months;
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
     * @return 获取当日指定时间的时间戳
     */
    public static long getDayByHour(long date, Integer hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hour, 0, 0);
        return calendar.getTime().getTime();

    }

    /**
     * @return 时间格式化 2020-08-04
     */
    public static String getDateInstance() {
        return new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
    }

    /**
     * @param date 当前时间的时间戳
     * @return 时间格式化 2020-08-04
     */
    public static String getDateInstance(long date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * @return 获取年时间格式化 2020
     */
    public static String getAddDayInstance(int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * @return 获取年时间格式化 2020
     */
    public static String getAddDayInstance(long time, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * @return 时间格式化 2020-08
     */
    public static String getMonthInstance() {
        return new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis());
    }

    /**
     * @param date 当前时间的时间戳
     * @return 时间格式化 2020-08
     */
    public static String getMonthInstance(long date) {
        return new SimpleDateFormat("yyyy-MM").format(date);
    }

    /**
     * @return 获取月时间格式化 2020-08
     */
    public static String getAddMonthInstance(int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * @return 获取月时间格式化 2020-08
     */
    public static String getAddMonthInstance(long time, int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * @return 时间格式化 2020
     */
    public static String getYearInstance() {
        return new SimpleDateFormat("yyyy").format(System.currentTimeMillis());
    }

    /**
     * @param date 当前时间的时间戳
     * @return 时间格式化 2020
     */
    public static String getYearInstance(long date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * @return 获取时间格式化 2020
     */
    public static String getAddYearInstance(int year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * @return 获取时间格式化 2020
     */
    public static String getAddYearInstance(long time, int year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * @param stime1 时间点1的起始时间
     * @param etime1 时间点1的结束时间
     * @param stime2 时间点2的起始时间
     * @param etime2 时间点2的结束时间
     * @return 获取两个时间段的交集区间 单位分钟
     */
    public static Integer getTimeInterval(Long stime1, Long etime1, Long stime2, Long etime2) {
        int f = 0;

        if (stime1 > etime1 || stime2 > etime2) {
            throw new RuntimeException("起始时间不能大于结束时间");
        }

        if (etime1 <= stime2 || stime1 >= etime2) {
            return f;
        }

        long[] a = {stime1, etime1, stime2, etime2};
        Arrays.sort(a); //从小到大排序，取第二、第三计算
        f = Math.toIntExact(a[2] - a[1]);

        return Math.round((f / 60000) * 100) / 100;
    }


    /**
     * @return 字符串转时间戳
     */
    public static Long getLongByString(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert dateTime != null;
        return dateTime.getTime();
    }

    /**
     * @return 字符串转时间戳
     */
    public static Long getLongByString(String time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert dateTime != null;
        return dateTime.getTime();
    }

    /**
     * 查询近几个月的月初和月末
     *
     * @param num 近五个月 num=5
     * @return
     */
    public static List<Map<String, Object>> getRecentMonths(int num) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        //当前时所在月份的第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        //设置startDate
        Calendar c = Calendar.getInstance();
        //设置endDate
        Calendar c2 = Calendar.getInstance();
        for (int i = 0; i < num; i++) {
            HashMap<String, Object> dateMap = new HashMap<String, Object>();
            //设置start
            //设置为1号,当前日期既为本月第一天
            c.set(Calendar.DAY_OF_MONTH, 1);
            //设置为0点0分0秒
            c.set(c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH), 0, 0, 0);
            //存入年月显示
            dateMap.put("yearMonthStr", format.format(c.getTime()));
            dateMap.put("startDate", c.getTime());
            //将c 设置为下个月
            c.add(c.DATE, -1);


            //设置end 为当前月的月底 23时59分59秒
            c2.set(Calendar.DAY_OF_MONTH, 1);
            //设置时间 23时59分59秒
            c2.set(c2.get(c.YEAR), c2.get(c.MONTH), c2.get(c.DAY_OF_MONTH), 23, 59, 59);
            //获得当前月最后一天
            c2.add(Calendar.MONTH, 1);
            c2.set(Calendar.DAY_OF_MONTH, 0);
            //存入map
            dateMap.put("endDate", c2.getTime());
            //获取当前时间的下一个月
            c2.add(Calendar.MONTH, -1);
            //将c2设置为当前时间上一个月
            c2.set(c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH));
            //放入集合
            maps.add(dateMap);

        }
        return null;
    }

    /**
     * @return 获取月初的时间戳
     */
    public static long getMonthBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1,
                23, 59, 59);
        return calendar.getTime().getTime();

    }

    /**
     * @return 获取月末的时间戳
     */
    public static long getMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 0,
                23, 59, 59);
        return calendar.getTime().getTime();
    }

    /**
     * @param date 当前时间的时间戳
     * @return 获取月初的时间戳
     */
    public static long getMonthBegin(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1,
                23, 59, 59);
        return calendar.getTime().getTime();

    }

    /**
     * @param date 当前时间的时间戳
     * @return 获取月末的时间戳
     */
    public static long getMonthEnd(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 0,
                23, 59, 59);
        return calendar.getTime().getTime();
    }
}

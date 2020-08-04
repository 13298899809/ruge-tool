package com.ruge.core;

import com.ruge.core.enums.OrderEnum;
import org.junit.Test;

import java.util.Date;

public class DateToolTest {

    @Test
    public void getDays() {
        /*2020-07-03 09:55:47*/
        long startTime = 1593741347000L;
        /*2020-07-20 09:55:47*/
        long endTime = 1595210147000L;
        /*2020-07-12 09:55:47*/
        long startStopTime = 1594518947000L;
        /*2020-07-18 09:55:47*/
        long endStopTime = 1595037347000L;
        OrderEnum orderEnum = OrderEnum.DESC;
        DateTool.getDays(startTime, endTime, startStopTime, endStopTime, orderEnum).forEach(System.out::println);
    }

    @Test
    public void addDay() {
        Date date = new Date(DateTool.addDay(1595210147000L, -10));
        System.out.println(date);
    }

    @Test
    public void addHour() {
        Date date = new Date(DateTool.addHour(1595210147000L, -10));
        System.out.println(date);
    }

    @Test
    public void addMinutes() {
        Date date = new Date(DateTool.addMinutes(1595210147000L, -10));
        System.out.println(date);
    }

    @Test
    public void getDateBegin() {
        long dateBegin = DateTool.getDayBegin(System.currentTimeMillis());
        System.out.println(dateBegin);
    }

    @Test
    public void getDayEnd() {
        long dateBegin = DateTool.getDayEnd(System.currentTimeMillis());
        System.out.println(dateBegin);
    }

    @Test
    public void getDateString() {
        String dateString = DateTool.getDateInstance(System.currentTimeMillis());
        System.out.println(dateString);
    }
}
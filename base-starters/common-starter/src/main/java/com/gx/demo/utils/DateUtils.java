package com.gx.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName : DateUtils
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/5/30 11:36
 * @Version : 1.0
 */
public class DateUtils {


    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }
    /**
     * 获取yyyyMM格式
     * @return
     */
    public static String getYearAndMonth() {
        return formatDate(new Date(), "yyyyMM");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     *
     * @return
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * @Title: compareDate
     * @Description:(日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return boolean
     * @throws
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }
    public static boolean compareDate(Date s, Date e) {
        if (s == null || s == null) {
            return false;
        }
        return s.getTime() >= s.getTime();
    }
    public static boolean compareDateNow(Date s) {
        if (s == null || s == null) {
            return false;
        }
        return s.getTime() >= System.currentTimeMillis();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseDate(String date) {
        return parse(date,"yyyy-MM-dd");
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseTime(String date) {
        return parse(date,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDate(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }




    /**
     * 增加固定的天数
     * @param date
     * @param type
     * @param value
     * @return
     */
    public static Date addDate(Date date,int type , int value){
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (date!=null) {
            canlendar.setTime(date);
        }
        canlendar.add(type, value); // 日期减 如果不够减会将月变动
        return canlendar.getTime();
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 获取一个月的第一天
     * @param date
     * @return
     */
    public static String getFirstDayMonth(Date date) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (date!=null) {
            canlendar.setTime(date);
        }
        canlendar.set(Calendar.DATE, 1);
        date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 获取一个月的最后一天
     * @param date
     * @return
     */
    public static String getLastDayMonth(Date date) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (date!=null) {
            canlendar.setTime(date);
        }
        canlendar.add(Calendar.MONTH, 1);
        canlendar.set(Calendar.DATE, 1);
        canlendar.add(Calendar.DATE, -1);
        date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 格式化Oracle Date
     * @return
     */
//    public static String buildDateValue(Object value){
//        if(Func.isOracle()){
//            return "to_date('"+ value +"','yyyy-mm-dd HH24:MI:SS')";
//        }else{
//            return Func.toStr(value);
//        }
//    }

    public static void main(String[] args) {
//
    }

}

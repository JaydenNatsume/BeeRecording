package com.utarlingtonserc.beerecording;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String YMD_FORMAT = "MM/dd/yyyy";

    public static final String YMD_FORMAT_2 = "yyyy.MM.dd";

    public static final String YM_FORMAT = "yyyy-MM";

    public static final String MD_FORMAT = "MM-dd";

    public static final String MD_FORMAT_2 = "MM.dd";

    public static final String HMS_FORMAT = "HH:mm:ss";

    public static final String HM_FORMAT = "HH:mm";

    public static final String YMD_HMS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YMD_HM_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String YMD_HMS_FORMAT_2 = "yyyyYMMMddD HH:mm:ss";

    public static final String YMD_HM_FORMAT_2 = "yyyyYMMMddD HH:mm";

    public static final String YMDHMS_FORMAT = "yyyyMMddHHmmss";

    /**
     * date转换为字符串，默认为 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, null);
    }

    /**
     * date转换为字符串
     *
     * @param date
     * @param format 格式
     * @return
     */
    public static String date2String(Date date, String format) {
        if (null == format) {
            // default format
            format = YMD_HMS_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 字符串转换为date
     *
     * @param date
     * @param format
     * @return
     */
    public static Date string2Date(String date, String format) {
        DateFormat inputDf = null;
        inputDf = new SimpleDateFormat(format, Locale.getDefault());

        Date result = null;
        try {
            result = inputDf.parse(date);
        } catch (ParseException e) {
        }
        return result;
    }

    /**
     * 获取当前时间Date
     *
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 日期变量转成对应的星期字符串
     *
     * @param date
     * @return
     */

    public static final int WEEKDAYS = 7;

    /**
     * 星期字符数组
     */
    public static String[] WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    public static String[] WEEK_2 = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    /**
     * 返回当前星期几
     *
     * @param date
     * @return
     */
    public static String date2Week(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    /**
     * 格式化时间
     *
     * @param dateTime
     * @return
     */

    /**
     * 将时间戳转换成字符串，默认yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String formatDateTimeMill(long dateTime) {
        return formatDateTimeMill(dateTime, YMD_HMS_FORMAT);
    }

    /**
     * 将时间戳转换成字符串，格式为yyyy-MM-dd HH:mm，即精确到分
     *
     * @param dateTime
     * @return
     */
    public static String formatDateTimeMin(long dateTime) {
        return formatDateTimeMill(dateTime, YMD_HM_FORMAT);
    }

    /**
     * 将时间戳转换成字符串
     *
     * @param dateTime
     * @param format   格式
     * @return
     */
    public static String formatDateTimeMill(long dateTime, String format) {
        Date date = new Date(dateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}

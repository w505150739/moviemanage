package com.movie.common.utils.date;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuyuzhu
 * @description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2018/1/22 11:04
 */
public class DateUtil {
    private static final Logger log = Logger.getLogger(DateUtil.class);

    private static SimpleDateFormat simpleDateFormat;
    private static Date date;
    private static Calendar calendar = null;
    public static final SimpleDateFormat YMDFormatter = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YMDHMSFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String DEFAULT_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期字符串转换为日期类型
     * @param dateStr 日期字符串
     * @param format 日期字符串格式
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr, String format) throws ParseException {
        simpleDateFormat = new SimpleDateFormat(format);
        date = (Date) simpleDateFormat.parse(dateStr);
        return date;
    }

    /**
     * 返回yyyy-MM-dd格式的时间字符串
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        return YMDFormatter.format(date);
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的时间字符串
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return YMDHMSFormatter.format(date);
    }

    /**
     * 将日期按照指定格式转换为字符串
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateStr(Date date,String formatStr){
        simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(date);
    }

    /**
     * 返回当前时间day天之后（day>0）或day天之前（day<0）的时间
     * @param date
     * @param day
     * @return
     */
    public static Date getDateD(Date date, int day) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 返回当前时间month个月之后（month>0）或month个月之前（month<0）的时间
     * @param date
     * @param month
     * @return
     */
    public static Date getDateM(Date date, int month) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 返回当前时间year年之后（year>0）或year年之前（year<0）的时间
     * @param date
     * @param year
     * @return
     */
    public static Date getDateY(Date date, int year) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 返回参数指定日期所属于的年份
     * @param date 日期
     * @return
     */
    public static int getYear(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回参数指定日期所属于的月份
     * @param date 日期
     * @return
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回参数指定日期是哪一天
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回参数指定时间的小时
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回参数指定时间的分钟
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回参数指定时间的秒
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 返回参数指定时间的长整形表示
     * @param date
     * @return
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static void main(String[] args) {

        Date d = new Date();
        System.out.println(getDateStr(d, "yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * 获取当前时间的 字符串 如 2013-01-02 21:21:21
     * @return
     */
    public static final String getNowTimestampStr() {
        return YMDHMSFormatter.format(new Timestamp(System.currentTimeMillis()));
    }


    /**
     *
     * @标题: getSqlTimestampDate
     * @描述: 把date转化Timestamp
     *
     * @参数信息
     *    @param nowDate
     *    @return
     *
     * @返回类型 Timestamp
     * @开发者 moysh
     * @可能抛出异常
     */
    public static Timestamp getSqlTimestampDate(Date nowDate) {
        if (nowDate == null) {
            nowDate = getNowDate(null);
        }
        // nowDate.gett
        Timestamp ts = new Timestamp(nowDate.getTime());
        // ts.getTime()
        // java.sql.Date date=new java.sql.Date(nowDate.getTime());
        return ts;
    }

    /**
     *
     * @标题: getDateByType
     * @描述: 取得当前格式日期字符串
     *
     * @参数信息
     *    @param dateType
     *    @return
     *
     * @返回类型 String
     * @开发者 moysh
     * @可能抛出异常
     */
    public static String getDateByType(String dateType) {
        String today = "";
        Date day = new Date();
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(
                dateType);
        today = bartDateFormat.format(day);
        return today;
    }

    /**
     * 比较两个日期大小
     *
     * @param d1
     * @param d2
     * @return
     */
    public static String compareDate(Long d1, Long d2) {
        String result = "";
        if (d2 > d1) {
            result = "G";
        } else if (d2 < d1) {
            result = "L";
        } else {
            result = "E";
        }
        return result;
    }

    // 给定一个日期型字符串，返回加减n天后的日期型字符串
    public static String nDaysAfterOneDateString(String basicDate, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmpDate = null;
        try {
            tmpDate = df.parse(basicDate);
        } catch (Exception e) {
            log.error("日期型字符串格式错误",e);
        }
        long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
                * (24 * 60 * 60 * 1000);
        tmpDate.setTime(nDay);

        return df.format(tmpDate);
    }


    /**
     * 日期字符串格式 变 日期
     *
     * @param s
     *            String
     * @return Date
     */
    public static Date getDate(String s, String datetype) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(datetype);
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            log.error(e.toString());
        }
        return date;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate(String dateType) {
        Date date = null;
        try {
            Date currentTime = new Date();
            //hjp 2014-9-23修改
            if (dateType == null || "".equals(dateType)) {
                dateType = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat formatter = new SimpleDateFormat(dateType);
            String dateString = formatter.format(currentTime);
            // ParsePosition pos = new ParsePosition(8);

            date = formatter.parse(dateString);
        } catch (ParseException e) {
            log.error(e.toString());
        }
        return date;
    }
    /**
     * 比较两个时间的先后 date1早于date2返回true；否则返回false
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compateTime(Date date1,Date date2){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar1.before(calendar2);

    }

    /**
     * hjp 2014-9-17
     * 返回当前日期 减指定日期date的天数，用于mysql中 SUBDATE(curdate(),INTERVAL ? DAY)的?
     * @param date
     * @return
     */
    public static int getPassDay(Date date) {
        if(date == null)
            return -1;//-1在mysql数据中表示当天
        YMDFormatter.set2DigitYearStart(new Date());
        int nowDay = YMDFormatter.getCalendar().get(Calendar.DAY_OF_MONTH);
        YMDFormatter.set2DigitYearStart(date);
        int thisDay = YMDFormatter.getCalendar().get(Calendar.DAY_OF_MONTH);
        return nowDay - thisDay;
    }

    /**
     * hjp 2014-9-17
     * 返回当前日期 减指定日期date的天数，用于mysql中 SUBDATE(curdate(),INTERVAL ? DAY)的?
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getPassDay(String date) throws ParseException {
        return getPassDay(parseDate(date, "yyyy-MM-dd"));
    }

    /**
     *
     * @标题: getDayForWeek
     * @描述: 星期几
     */
    public static int getDayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try
        {
            c.setTime(format.parse(pTime));
        } catch (ParseException e)
        {
            log.error(e.toString());
        }
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
}

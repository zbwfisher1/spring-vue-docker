package com.zbwfisher.datasource.common.sqlTools;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zbw on 17/5/4.
 */
public class DateUtil {

    public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";
    public static final String HOUR_MINUTE_SECOND_PATTERN = "HH:mm:ss";
    public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static int getYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(1);
    }

    public static int getMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(2) + 1;
    }

    public static int getDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(5);
    }

    public static int getHour(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(10);
    }

    public static int getMinute(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(12);
    }

    public static int getSecond(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(13);
    }

    public static Integer getYearMonth(Date date)
    {
        return new Integer(format(date, "yyyyMM"));
    }

    public static Date parseYearMonth(Integer yearMonth)
            throws ParseException
    {
        return parse(String.valueOf(yearMonth), "yyyyMM");
    }

    public static Date addYear(Date date, int ammount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(1, ammount);
        return c.getTime();
    }

    public static Date addMonth(Date date, int ammount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, ammount);
        return c.getTime();
    }

    public static Date addDay(Date date, int ammount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, ammount);
        return c.getTime();
    }

    public static Integer addMonth(Integer yearMonth, int ammount)
            throws ParseException
    {
        return getYearMonth(addMonth(parseYearMonth(yearMonth), ammount));
    }

    public static int beforeYears(Date beforeDate, Date afterDate)
    {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        beforeCalendar.set(2, 1);
        beforeCalendar.set(5, 1);
        beforeCalendar.set(10, 0);
        beforeCalendar.set(13, 0);
        beforeCalendar.set(12, 0);
        Calendar afterCalendar = Calendar.getInstance();
        afterCalendar.setTime(afterDate);
        afterCalendar.set(2, 1);
        afterCalendar.set(5, 1);
        afterCalendar.set(10, 0);
        afterCalendar.set(13, 0);
        afterCalendar.set(12, 0);
        boolean positive = true;
        if (beforeDate.after(afterDate))
            positive = false;
        int beforeYears = 0;
        while (true) {
            boolean yearEqual = beforeCalendar.get(1) == afterCalendar.get(1);
            if (yearEqual) {
                break;
            }
            if (positive) {
                beforeYears++;
                beforeCalendar.add(1, 1);
            } else {
                beforeYears--;
                beforeCalendar.add(1, -1);
            }
        }

        return beforeYears;
    }

    public static int beforeMonths(Date beforeDate, Date afterDate)
    {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        beforeCalendar.set(5, 1);
        beforeCalendar.set(10, 0);
        beforeCalendar.set(13, 0);
        beforeCalendar.set(12, 0);
        Calendar afterCalendar = Calendar.getInstance();
        afterCalendar.setTime(afterDate);
        afterCalendar.set(5, 1);
        afterCalendar.set(10, 0);
        afterCalendar.set(13, 0);
        afterCalendar.set(12, 0);
        boolean positive = true;
        if (beforeDate.after(afterDate))
            positive = false;
        int beforeMonths = 0;
        while (true) {
            boolean yearEqual = beforeCalendar.get(1) == afterCalendar.get(1);
            boolean monthEqual = beforeCalendar.get(2) == afterCalendar.get(2);
            if ((yearEqual) && (monthEqual)) {
                break;
            }
            if (positive) {
                beforeMonths++;
                beforeCalendar.add(2, 1);
            } else {
                beforeMonths--;
                beforeCalendar.add(2, -1);
            }
        }

        return beforeMonths;
    }

    public static int beforeDays(Date beforeDate, Date afterDate)
    {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        beforeCalendar.set(10, 0);
        beforeCalendar.set(13, 0);
        beforeCalendar.set(12, 0);
        Calendar afterCalendar = Calendar.getInstance();
        afterCalendar.setTime(afterDate);
        afterCalendar.set(10, 0);
        afterCalendar.set(13, 0);
        afterCalendar.set(12, 0);
        boolean positive = true;
        if (beforeDate.after(afterDate))
            positive = false;
        int beforeDays = 0;
        while (true) {
            boolean yearEqual = beforeCalendar.get(1) == afterCalendar.get(1);
            boolean monthEqual = beforeCalendar.get(2) == afterCalendar.get(2);
            boolean dayEqual = beforeCalendar.get(5) == afterCalendar.get(5);
            if ((yearEqual) && (monthEqual) && (dayEqual)) {
                break;
            }
            if (positive) {
                beforeDays++;
                beforeCalendar.add(5, 1);
            } else {
                beforeDays--;
                beforeCalendar.add(5, -1);
            }
        }

        return beforeDays;
    }

    public static int beforeRoundYears(Date beforeDate, Date afterDate)
    {
        Date bDate = beforeDate;
        Date aDate = afterDate;
        boolean positive = true;
        if (beforeDate.after(afterDate)) {
            positive = false;
            bDate = afterDate;
            aDate = beforeDate;
        }
        int beforeYears = beforeYears(bDate, aDate);

        int bMonth = getMonth(bDate);
        int aMonth = getMonth(aDate);
        if (aMonth < bMonth) {
            beforeYears--;
        } else if (aMonth == bMonth) {
            int bDay = getDay(bDate);
            int aDay = getDay(aDate);
            if (aDay < bDay) {
                beforeYears--;
            }
        }

        if (positive) {
            return beforeYears;
        }
        return new BigDecimal(beforeYears).negate().intValue();
    }

    public static int beforeRoundAges(Date beforeDate, Date afterDate)
    {
        Date bDate = beforeDate;
        Date aDate = afterDate;
        boolean positive = true;
        if (beforeDate.after(afterDate)) {
            positive = false;
            bDate = afterDate;
            aDate = beforeDate;
        }
        int beforeYears = beforeYears(bDate, aDate);

        int bMonth = getMonth(bDate);
        int aMonth = getMonth(aDate);
        if (aMonth < bMonth) {
            beforeYears--;
        }

        if (positive) {
            return beforeYears;
        }
        return new BigDecimal(beforeYears).negate().intValue();
    }

    public static int beforeRoundMonths(Date beforeDate, Date afterDate)
    {
        Date bDate = beforeDate;
        Date aDate = afterDate;
        boolean positive = true;
        if (beforeDate.after(afterDate)) {
            positive = false;
            bDate = afterDate;
            aDate = beforeDate;
        }
        int beforeMonths = beforeMonths(bDate, aDate);

        int bDay = getDay(bDate);
        int aDay = getDay(aDate);
        if (aDay < bDay) {
            beforeMonths--;
        }

        if (positive) {
            return beforeMonths;
        }
        return new BigDecimal(beforeMonths).negate().intValue();
    }

    public static Date getDate(int year, int month, int date)
    {
        Calendar c = Calendar.getInstance();
        c.set(year + 1900, month, date);
        return c.getTime();
    }

    public static String format(Date date, String pattern)
    {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String format(Date date)
    {
        return format(date, "yyyy-MM-dd");
    }

    public static Date parse(String dateStr, String pattern)
            throws ParseException
    {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(dateStr);
    }

    public static Date parse(String dateStr)
            throws ParseException
    {
        if (dateStr.length() == "yyyy-MM-dd".length())
            return parse(dateStr, "yyyy-MM-dd");
        if (dateStr.length() == "yyyy-MM-dd HH:mm:ss".length()) {
            return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
        }
        return parse(dateStr, "yyyy-MM-dd");
    }

    public static boolean isYearMonth(Integer yearMonth)
    {
        String yearMonthStr = yearMonth.toString();
        return isYearMonth(yearMonthStr);
    }

    public static boolean isYearMonth(String yearMonthStr)
    {
        if (yearMonthStr.length() != 6) {
            return false;
        }
        String yearStr = yearMonthStr.substring(0, 4);
        String monthStr = yearMonthStr.substring(4, 6);
        try {
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            if ((year < 1800) || (year > 3000)) {
                return false;
            }

            return (month >= 1) && (month <= 12);
        }
        catch (Exception localException)
        {
        }
        return false;
    }

    public static List getYearMonths(Integer from, Integer to)
            throws ParseException
    {
        List yearMonths = new ArrayList();
        Date fromDate = parseYearMonth(from);
        Date toDate = parseYearMonth(to);
        if (fromDate.after(toDate))
            throw new IllegalArgumentException("'from' date should before 'to' date!");
        Date tempDate = fromDate;
        while (tempDate.before(toDate)) {
            yearMonths.add(getYearMonth(tempDate));
            tempDate = addMonth(tempDate, 1);
        }
        if (!from.equals(to)) {
            yearMonths.add(to);
        }

        return yearMonths;
    }

    public static Timestamp parseTimestamp(String dateStr, String pattern)
            throws ParseException
    {
        return new Timestamp(parse(dateStr, pattern).getTime());
    }

    public static Timestamp parseTimestamp(String dateStr)
            throws ParseException
    {
        return new Timestamp(parse(dateStr).getTime());
    }
}

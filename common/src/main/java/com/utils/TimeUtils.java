package com.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class TimeUtils {
    public static final long DAYMILLI = 86400000L;
    public static final long HOURMILLI = 3600000L;
    public static final long MINUTEMILLI = 60000L;
    public static final long SECONDMILLI = 1000L;
    public static final String TIMETO = " 23:59:59";
    public static final String FOREVER_TIME = "3000-12-31 23:59:59";
    public static final transient int BEFORE = 1;
    public static final transient int AFTER = 2;
    public static final transient int EQUAL = 3;
    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";
    public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";
    public static final String DATE_FMT_0 = "yyyyMMdd";
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";
    public static final String DATE_FMT_3 = "yyyy-MM-dd";
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";
    public static final String DATE_FMT_6 = "yyyy-MM";
    public static final String DATE_FMT_7 = "MM月dd日 HH:mm";
    public static final String DATE_FMT_8 = "HH:mm:ss";
    public static final String DATE_FMT_9 = "yyyy.MM.dd";
    public static final String DATE_FMT_10 = "HH:mm";
    public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";
    public static final String DATE_FMT_12 = "MM月dd日";
    public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";
    public static final String DATE_FMT_14 = "yyyyMM";
    public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";
    public static final String DATE_FMT_16 = "yyyyMMddHHmm";
    public static final String DATE_FMT_17 = "HHmmss";
    public static final String DATE_FMT_18 = "yyyy";
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public TimeUtils() {
    }

    public static String now() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = localDateTime.format(formatter);
        return now;
    }

    public static String now(String format) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String now = localDateTime.format(formatter);
        return now;
    }

    public static String today() {
        return now("yyyy-MM-dd");
    }

    public static String addMonths(String dateTime, String pattern, int month) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate time = LocalDate.parse(dateTime, dateTimeFormatter);
        LocalDate nextTime = time.plus((long)month, ChronoUnit.MONTHS);
        return nextTime.format(dateTimeFormatter);
    }

    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(formatter);
        } catch (DateTimeParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String formatLocalDateToString(LocalDate localDate, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return localDate.format(formatter);
        } catch (DateTimeParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static int getActualMaximum(Date date) {
        return dateToLocalDateTime(date).getMonth().length(dateToLocalDate(date).isLeapYear());
    }

    public static int getWeekOfDate(Date date) {
        return dateToLocalDateTime(date).getDayOfWeek().getValue();
    }

    public static long getAbsDateDiffDay(LocalDate before, LocalDate after) {
        return Math.abs(ChronoUnit.DAYS.between(before, after));
    }

    public static int getAbsTimeDiffDay(LocalDateTime before, LocalDateTime after) {
        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getDays());
    }

    public static int getAbsTimeDiffMonth(LocalDateTime before, LocalDateTime after) {
        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getMonths());
    }

    public static int getAbsTimeDiffYear(LocalDateTime before, LocalDateTime after) {
        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getYears());
    }

    public static int getAbsDateDiffYear(LocalDate before, LocalDate after) {
        return Math.abs(Period.between(before, after).getYears());
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(7);
    }

    public static int getLastMonth(Date date) {
        return dateToLocalDateTime(date).getMonth().getValue();
    }

    public static LocalDate newThisMonth(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    public static LocalDate lastThisMonth(Date date) {
        int lastDay = getActualMaximum(date);
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), lastDay);
    }

    public static LocalDate newThisYear(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), 1, 1);
    }

    public static Timestamp getCurrentDateTime() {
        return new Timestamp(Instant.now().toEpochMilli());
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime reserveDateCustomTime(Date date, String customTime) {
        String dateStr = dateToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDateTime reserveDateCustomTime(Timestamp date, String customTime) {
        String dateStr = timestampToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static final LocalDateTime zerolizedTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    public static LocalDateTime zerolizedTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    public static LocalDateTime getEndTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999000000);
    }

    public static LocalDateTime getEndTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999000000);
    }

    public static int calculateToEndTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime end = getEndTime(date);
        return (int)(end.toEpochSecond(ZoneOffset.UTC) - localDateTime.toEpochSecond(ZoneOffset.UTC));
    }

    public static LocalDateTime addTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int num) {
        return localDateTime.plus((long)num, chronoUnit);
    }

    public static String addTime(String date, String format, ChronoUnit chronoUnit, int num) {
        LocalDateTime localDateTime = stringToLocalDateTime(date, format);
        LocalDateTime plusDateTime = localDateTime.plus((long)num, chronoUnit);
        return formatLocalDateTimeToString(plusDateTime, format);
    }

    public static LocalDateTime addTime(Date date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = date.getTime() % 1000L * 1000000L;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000L, (int)nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus((long)num, chronoUnit);
    }

    public static LocalDateTime addTime(Timestamp date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = date.getTime() % 1000L * 1000000L;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000L, (int)nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus((long)num, chronoUnit);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        long nanoOfSecond = date.getTime() % 1000L * 1000000L;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000L, (int)nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime;
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp date) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000L, date.getNanos(), ZoneOffset.of("+8"));
        return localDateTime;
    }

    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDateTime(date).toLocalDate();
    }

    public static LocalDate timestampToLocalDate(Timestamp date) {
        return timestampToLocalDateTime(date).toLocalDate();
    }

    public static boolean isTheSameDay(LocalDateTime begin, LocalDateTime end) {
        return begin.toLocalDate().equals(end.toLocalDate());
    }

    public static int compareTwoTime(LocalDateTime time1, LocalDateTime time2) {
        if (time1.isAfter(time2)) {
            return 1;
        } else {
            return time1.isBefore(time2) ? -1 : 0;
        }
    }

    public static int compareTwoTime(String date1, String date2) {
        LocalDateTime localDateTime1 = stringToLocalDateTime(date1, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime2 = stringToLocalDateTime(date2, "yyyy-MM-dd HH:mm:ss");
        if (localDateTime1.isAfter(localDateTime2)) {
            return 1;
        } else {
            return localDateTime1.isBefore(localDateTime2) ? -1 : 0;
        }
    }

    public static long getTwoTimeDiffSecond(Timestamp time1, Timestamp time2) {
        long diff = timestampToLocalDateTime(time1).toEpochSecond(ZoneOffset.UTC) - timestampToLocalDateTime(time2).toEpochSecond(ZoneOffset.UTC);
        return diff > 0L ? diff : 0L;
    }

    public static long getTwoTimeDiffMin(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 60L;
        return diff > 0L ? diff : 0L;
    }

    public static long getTwoTimeDiffHour(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 3600L;
        return diff > 0L ? diff : 0L;
    }

    public static boolean isTimeInRange(Date startTime, Date endTime) {
        LocalDateTime now = getCurrentLocalDateTime();
        LocalDateTime start = dateToLocalDateTime(startTime);
        LocalDateTime end = dateToLocalDateTime(endTime);
        return start.isBefore(now) && end.isAfter(now) || start.isEqual(now) || end.isEqual(now);
    }

    public static long strToTime4DateTime(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldtStart = LocalDateTime.parse(date, dateTimeFormatter);
        return ldtStart.toEpochSecond(ZoneOffset.of("+8"));
    }

    public static LocalDateTime getForeverTime() {
        return stringToLocalDateTime("3000-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        System.out.println(getAbsTimeDiffYear(stringToLocalDateTime("1982-03-11 00:00:00", "yyyy-MM-dd HH:mm:ss"), getCurrentLocalDateTime()));
    }
}


package net.bi4vmr.tool.java.common.base;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间与日期工具类。
 *
 * @author BI4VMR@outlook.com
 */
public class DateTimeUtil {

    /*
     * 时间范围
     */

    /**
     * 最小日期
     */
    public static final String MIN_DATE = "1970-1-1";

    /**
     * 最大日期
     */
    public static final String MAX_DATE = "2099-12-31";

    /**
     * 一天的起点时刻
     */
    public static final String MIN_TIME = "00:00:00";

    /**
     * 一天的终点时刻
     */
    public static final String MAX_TIME = "23:59:59";

    /**
     * 最小时间与日期
     */
    public static final String MIN_DATETIME = MIN_DATE + " " + MIN_TIME;

    /**
     * 最大时间与日期
     */
    public static final String MAX_DATETIME = MAX_DATE + "" + MAX_TIME;

    /*
     * 字符串格式
     */

    /**
     * 默认的日期格式
     */
    public static final String FORMAT_DATE_DEFAULT = "yyyy-MM-dd";

    /**
     * 默认的时间格式
     */
    public static final String FORMAT_TIME_DEFAULT = "HH:mm:ss";

    /**
     * 默认的时间与日期格式
     */
    public static final String FORMAT_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /*
     * 星期X显示格式
     */

    /**
     * 显示样式枚举
     */
    public enum WEEKDAY_STYLE {
        CHINESE_1,
        CHINESE_2,
        ENGLISH
    }

    /* 周的名称 */
    private static final String[] dayNames_CHI1 = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private static final String[] dayNames_CHI2 = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private static final String[] dayNames_ENG = {"Mon.", "Tues.", "Wed.", "Thur.", "Fri.", "Sat.", "Sun."};

    /**
     * 获取当前时间与日期的格式化文本（默认格式）。
     * <p>
     * 获取当前时间与日期的格式化文本，形如："2020-01-01 12:30:00"。
     *
     * @return 时间与日期文本。
     */
    public static String getDateTimeText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME_DEFAULT);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 获取当前日期的格式化文本（默认格式）。
     * <p>
     * 获取当前日期的格式化文本，形如："2020-01-01"。
     *
     * @return 日期文本。
     */
    public static String getDateText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);
        return LocalDate.now().format(formatter);
    }

    /**
     * 获取当前时间的格式化文本（默认格式）。
     * <p>
     * 获取当前时间的格式化文本，形如："12:30:00"。
     *
     * @return 时间文本。
     */
    public static String getTimeText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIME_DEFAULT);
        return LocalTime.now().format(formatter);
    }

    /**
     * 获取指定日期的格式化文本（默认格式）。
     * <p>
     * 获取指定日期的格式化文本，形如："2020-01-01"。
     *
     * @param year  年份。
     * @param month 月份。
     * @param day   日期。
     * @return 日期文本。
     */
    public static String getDateText(int year, int month, int day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);
        return LocalDate.of(year, month, day).format(formatter);
    }

    /**
     * 获取指定时间的格式化文本（默认格式）。
     * <p>
     * 获取指定时间的格式化文本，形如："12:30:00"。
     *
     * @param hour 时。
     * @param min  分。
     * @param sec  秒。
     * @return 时间文本。
     */
    public static String getTimeText(int hour, int min, int sec) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIME_DEFAULT);
        return LocalTime.of(hour, min, sec).format(formatter);
    }

    /**
     * 获取LocalDateTime实例。
     * <p>
     * 将标准格式（"yyyy-MM-dd HH:mm:ss"）时间日期文本转换为LocalDateTime实例。
     *
     * @param rawData 原始文本。
     * @return LocalDateTime实例。
     */
    public static LocalDateTime getLocalDateTime(String rawData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME_DEFAULT);
        return LocalDateTime.parse(rawData, formatter);
    }

    /**
     * 获取LocalDate实例。
     * <p>
     * 将标准格式（"yyyy-MM-dd"）日期文本转换为LocalDate实例。
     *
     * @param rawData 原始数据。
     * @return LocalDate实例。
     */
    public static LocalDate getLocalDate(String rawData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);
        return LocalDate.parse(rawData, formatter);
    }

    /**
     * 获取LocalTime实例。
     * <p>
     * 将标准格式（"HH:mm:ss"）时间文本转换为LocalTime实例。
     *
     * @param rawData 原始数据。
     * @return LocalTime实例。
     */
    public static LocalTime getLocalTime(String rawData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIME_DEFAULT);
        return LocalTime.parse(rawData, formatter);
    }

    /**
     * 获取当前月度的日期范围。
     *
     * @return 第一元素为本月第一天，第二元素为本月最后一天。
     */
    public static String[] getDateRangeOfMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);
        LocalDate localDate = LocalDate.now();

        LocalDate sLocalDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate eLocalDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        String sDate = sLocalDate.format(formatter);
        String eDate = eLocalDate.format(formatter);
        return new String[]{sDate, eDate};
    }

    /**
     * 获取指定月度的日期范围。
     *
     * @param year  年份，格式为"yyyy"。
     * @param month 月份，格式为"MM"。
     * @return 第一元素为本月第一天，第二元素为本月最后一天。
     */
    public static String[] getDateRangeOfMonth(String year, String month) {
        month = NumberUtil.toSerialNumber(Integer.parseInt(month), 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME_DEFAULT);

        LocalDate localDate = LocalDate.parse(year + "-" + month + "-01", formatter);
        LocalDate sLocalDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate eLocalDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        String sDate = sLocalDate.format(formatter);
        String eDate = eLocalDate.format(formatter);
        return new String[]{sDate, eDate};
    }

    /**
     * 获取当前月度的时间范围。
     *
     * @return 第一元素为本月第一天，第二元素为本月最后一天。
     */
    public static String[] getTimeRangeOfMonth() {
        String[] dateRange = getDateRangeOfMonth();
        String sDate = dateRange[0] + " " + MIN_TIME;
        String eDate = dateRange[1] + " " + MAX_TIME;
        return new String[]{sDate, eDate};
    }

    /**
     * 获取指定月度的日期范围。
     *
     * @param year  年份，格式为"yyyy"。
     * @param month 月份，格式为"MM"。
     * @return 第一元素为本月第一天，第二元素为本月最后一天。
     */
    public static String[] getTimeRangeOfMonth(String year, String month) {
        String[] dateRange = getDateRangeOfMonth(year, month);
        String sDate = dateRange[0] + " " + MIN_TIME;
        String eDate = dateRange[1] + " " + MAX_TIME;
        return new String[]{sDate, eDate};
    }

    /**
     * 获取当前季度的日期范围。
     *
     * @return 第一元素为本季度第一天，第二元素为本季度最后一天。
     */
    public static String[] getDateRangeOfQuarter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);

        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        String sDate;
        String eDate;
        // 根据当前月份所属季度，计算时间范围。
        if (month <= 3) {
            sDate = year + "-01-01";
            // 将日期设置为3月
            LocalDate eLocalDate = LocalDate.parse(year + "-03-01", formatter);
            eLocalDate = eLocalDate.with(TemporalAdjusters.lastDayOfMonth());
            eDate = eLocalDate.format(formatter);
        } else if (month <= 6) {
            sDate = year + "-04-01";
            // 将日期设置为6月
            LocalDate eLocalDate = LocalDate.parse(year + "-06-01", formatter);
            eLocalDate = eLocalDate.with(TemporalAdjusters.lastDayOfMonth());
            eDate = eLocalDate.format(formatter);
        } else if (month <= 9) {
            sDate = year + "-07-01";
            // 将日期设置为9月
            LocalDate eLocalDate = LocalDate.parse(year + "-09-01", formatter);
            eLocalDate = eLocalDate.with(TemporalAdjusters.lastDayOfMonth());
            eDate = eLocalDate.format(formatter);
        } else {
            sDate = year + "-10-01";
            // 将日期设置为12月
            LocalDate eLocalDate = LocalDate.parse(year + "-12-01", formatter);
            eLocalDate = eLocalDate.with(TemporalAdjusters.lastDayOfMonth());
            eDate = eLocalDate.format(formatter);
        }

        return new String[]{sDate, eDate};
    }

    /**
     * 获取当前季度的时间范围。
     *
     * @return 第一元素为本季度第一天，第二元素为本季度最后一天。
     */
    public static String[] getTimeRangeOfQuarter() {
        String[] dateRange = getDateRangeOfQuarter();
        String sDate = dateRange[0] + " " + MIN_TIME;
        String eDate = dateRange[1] + " " + MAX_TIME;
        return new String[]{sDate, eDate};
    }

    /**
     * 获取当前年度的日期范围。
     *
     * @return 第一元素为本年度第一天，第二元素为本年度最后一天。
     */
    public static String[] getDateRangeOfYear() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);

        LocalDate localDate = LocalDate.now();
        LocalDate sLocalDate = localDate.with(TemporalAdjusters.firstDayOfYear());
        LocalDate eLocalDate = localDate.with(TemporalAdjusters.lastDayOfYear());
        String sDate = sLocalDate.format(formatter);
        String eDate = eLocalDate.format(formatter);
        return new String[]{sDate, eDate};
    }

    /**
     * 获取当前年度的时间范围。
     *
     * @return 第一元素为本年度第一天，第二元素为本年度最后一天。
     */
    public static String[] getTimeRangeOfYear() {
        String[] dateRange = getDateRangeOfYear();
        String sDate = dateRange[0] + " " + MIN_TIME;
        String eDate = dateRange[1] + " " + MAX_TIME;
        return new String[]{sDate, eDate};
    }

    /**
     * 获取指定年度的时间范围。
     *
     * @param year 年份，格式为"yyyy"。
     * @return 第一元素为本年度第一天，第二元素为本年度最后一天。
     */
    public static String[] getDateRangeOfYear(String year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);

        LocalDate localDate = LocalDate.parse(year + "-01-01", formatter);
        LocalDate sLocalDate = localDate.with(TemporalAdjusters.firstDayOfYear());
        LocalDate eLocalDate = localDate.with(TemporalAdjusters.lastDayOfYear());
        String sDate = sLocalDate.format(formatter);
        String eDate = eLocalDate.format(formatter);
        return new String[]{sDate, eDate};
    }

    /**
     * 获取指定年度的时间范围。
     *
     * @param year 年份，格式为"yyyy"。
     * @return 第一元素为本年度第一天，第二元素为本年度最后一天。
     */
    public static String[] getTimeRangeOfYear(String year) {
        String[] dateRange = getDateRangeOfYear(year);
        String sDate = dateRange[0] + " " + MIN_TIME;
        String eDate = dateRange[1] + " " + MAX_TIME;
        return new String[]{sDate, eDate};
    }

    /**
     * 将SQL时间戳转换为标准格式文本。
     *
     * @param timestamp SQL时间戳。
     * @return 时间与日期的格式化文本。
     */
    public static String toDateTimeText(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME_DEFAULT);
        return timestamp.toLocalDateTime().format(formatter);
    }

    /**
     * 将SQL时间戳转换为标准格式文本。
     *
     * @param timestamp SQL时间戳。
     * @return 日期的格式化文本。
     */
    public static String toDateText(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_DEFAULT);
        return timestamp.toLocalDateTime().toLocalDate().format(formatter);
    }

    /**
     * 将SQL时间戳转换为标准格式文本。
     *
     * @param timestamp SQL时间戳。
     * @return 时间的格式化文本。
     */
    public static String toTimeText(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIME_DEFAULT);
        return timestamp.toLocalDateTime().toLocalTime().format(formatter);
    }

    /**
     * 获取一周中某一天的名称。
     * <p>
     * 名称风格：取值为CHINESE_1时，“周X”；取值为CHINESE_2时，“星期X”；取值为ENGLISH时，为英文缩写。
     *
     * @param localDate LocalDate实例。
     * @param style     名称风格。
     * @return 名称。
     */
    public static String getWeekDayName(LocalDate localDate, WEEKDAY_STYLE style) {
        int i = localDate.getDayOfWeek().getValue() - 1;
        String result = "";
        switch (style) {
            case CHINESE_1:
                result = dayNames_CHI1[i];
                break;
            case CHINESE_2:
                result = dayNames_CHI2[i];
                break;
            case ENGLISH:
                result = dayNames_ENG[i];
                break;
        }
        return result;
    }

    /**
     * 获取一周中某一天的名称。
     * <p>
     * 名称风格：取值为CHINESE_1时，“周X”；取值为CHINESE_2时，“星期X”；取值为ENGLISH时，为英文缩写。
     *
     * @param localDateTime LocalDateTime实例。
     * @param style         名称风格。
     * @return 名称。
     */
    public static String getWeekDayName(LocalDateTime localDateTime, WEEKDAY_STYLE style) {
        return getWeekDayName(localDateTime.toLocalDate(), style);
    }
}

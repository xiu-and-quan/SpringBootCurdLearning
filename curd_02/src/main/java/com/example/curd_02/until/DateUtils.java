package com.example.curd_02.until;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public final class DateUtils {

    private DateUtils() {}

    public enum Pattern {
        /**
         * yyyy-MM-dd
         */
        YYYY_MM_DD("yyyy-MM-dd"),
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        /**
         * yyyy-MM-dd HH:mm:ss.SSS
         */
        YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
        /**
         * 默认格式：yyyy-MM-dd HH:mm:ss
         */
        DEFAULT("yyyy-MM-dd HH:mm:ss"),
        ;
        String format;
        Pattern(String format) {
            this.format = format;
        }
        String format() {
            return format;
        }
    }

    /**
     * 日期字符串转 java.time.LocalDateTime
     *
     * @param datetimeStr 日期字符串
     * @param pattern 格式
     * @return 日期字符串对应的 LocalDateTime
     * @throws java.time.format.DateTimeParseException
     */
    public static LocalDateTime toLocalDateTime(String datetimeStr, Pattern pattern) {
        if (StringUtils.isEmpty(datetimeStr)) {
            return null;
        }

        if (Objects.equals(pattern, Pattern.YYYY_MM_DD)) {
            datetimeStr = datetimeStr + " 00:00:00";
            pattern = Pattern.YYYY_MM_DD_HH_MM_SS;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.format());
        return LocalDateTime.parse(datetimeStr, formatter);
    }

    /**
     * 日期字符串转 java.util.Date
     *
     * @param datetimeStr 日期字符串
     * @param pattern 格式
     * @return 日期字符串对应的 Date
     * @throws IllegalArgumentException
     */
    public static Date toDate(String datetimeStr, Pattern pattern) {
        if (StringUtils.isEmpty(datetimeStr)) {
            return null;
        }

        if (Objects.isNull(pattern)) {
            pattern = Pattern.DEFAULT;
        }

        try {
            return new SimpleDateFormat(pattern.format()).parse(datetimeStr);
        } catch(Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * SimpleDateFormat是日期格式化工具，里面有format方法
     * public static String toShortDateString(Date dt){
     *         SimpleDateFormat myFmt=new SimpleDateFormat("yy年MM月dd日 HH时mm分");
     *         return myFmt.format(dt);//format是转成字符串
     *         .parse()是转成日期类型，将字符串类型转成日期类型
     *     }
     */

    public static String dateFormatToString(Date date, Pattern pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.format());
        return simpleDateFormat.format(date);
    }

    /**
     * LocalDateTime 转字符串
     *
     * @param localDateTime
     * @param pattern
     * @return null if {@code localDateTime} is null
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, Pattern pattern) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }

        if (Objects.isNull(pattern)) {
            pattern = Pattern.DEFAULT;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.format());
        return formatter.format(localDateTime);
    }
}


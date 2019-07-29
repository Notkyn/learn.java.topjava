package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static boolean isFilter(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, LocalDateTime current){
        boolean sD = startDate == null || startDate.equals(current.toLocalDate()) || current.toLocalDate().isAfter(startDate);
        boolean eD = endDate == null || endDate.equals(current.toLocalDate()) || current.toLocalDate().isBefore(endDate);
        boolean sT = startTime == null || startTime.equals(current.toLocalTime()) || current.toLocalTime().isAfter(startTime);
        boolean eT = endTime == null || endTime.equals(current.toLocalTime()) || current.toLocalTime().isBefore(endTime);

        return sD && eD && sT && eT;
    }
}

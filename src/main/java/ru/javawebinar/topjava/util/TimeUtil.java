package ru.javawebinar.topjava.util;

import java.time.LocalTime;

/**
 * Класс для работы с датами
 */
public class TimeUtil {
    /**
     * Проверка даты на вхождение в заданый промежуток
     * @param lt - проверяемая дата
     * @param startTime - начало периода
     * @param endTime з- конец периода
     * @return - результат проверки - вхождение в период или нет
     */
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}

package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * Бин еды с лимитом по калориям
 */
public class UserMealWithExceed {
    // Время для еды
    private final LocalDateTime dateTime;
    // Описание
    private final String description;
    // Колорийность
    private final int calories;
    // Превышает ли сумма калорий за весь день параметра метода caloriesPerDay
    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}

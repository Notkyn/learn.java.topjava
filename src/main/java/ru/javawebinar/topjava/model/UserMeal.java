package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * Бин еды
 */
public class UserMeal {
    // Время для еды
    private final LocalDateTime dateTime;
    // Описание
    private final String description;
    // Уровень калорий
    private final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}

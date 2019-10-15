package ru.javawebinar.topjava.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MealBind extends BaseTo implements Serializable {
    private LocalDateTime dateTime;
    private String description;
    private int calories;

    public MealBind() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(String value) {
        this.dateTime = LocalDateTime.parse(value);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "MealBind{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", id=" + id +
                '}';
    }
}

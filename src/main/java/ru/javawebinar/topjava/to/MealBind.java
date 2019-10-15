package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class MealBind extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDateTime dateTime;

    @NotBlank
    @Size(min = 2, max = 120, message = "length must be between 2 and 120 characters")
    private String description;

    @Range(min = 10, max = 5000, message = "size must be between 10 and 5000 value")
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

package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal, int userId);
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Meal get(int id, int userId);

    Collection<Meal> getAll();

    Collection<Meal> getUserMeals(int userId);
    Collection<Meal> getUserMealsFiltered(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);

    boolean contsindUserMeal(int userId);
}

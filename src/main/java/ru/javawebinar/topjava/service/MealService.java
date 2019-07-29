package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealService {
    Meal create(Meal meal, int userId);
    void delete(int id, int userId) throws NotFoundException;
    void update(Meal meal, int userId);
    Meal get(int id, int userId) throws NotFoundException;
    Collection<Meal> getUserMeals(int userId) throws NotFoundException;
    Collection<Meal> getUserMealsFiltered(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) throws NotFoundException;
    Collection<Meal> getAll();
}
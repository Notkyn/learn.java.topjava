package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Set;

public interface Dao {
    void add(Meal meal);
    Meal getById(int id);
    List<Meal> getAllMeals();
    Set<Integer> getAllId();
    void update(Meal meal);
    void delete(Meal meal);
    void deleteById(int id);
}

package ru.javawebinar.topjava.dao.imp;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.data.MapStorage;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Set;

public class MapDaoImp implements Dao {

    @Override
    public void add(Meal meal){
        MapStorage.getInstance().add(meal);
    }

    @Override
    public Meal getById(int id){
        return MapStorage.getInstance().getById(id);
    }

    @Override
    public List<Meal> getAllMeals(){
        return MapStorage.getInstance().getAllMeals();
    }

    @Override
    public Set<Integer> getAllId(){
        return MapStorage.getInstance().getAllId();
    }

    @Override
    public void update(Meal meal){
        MapStorage.getInstance().update(meal);
    }

    @Override
    public void delete(Meal meal){
        MapStorage.getInstance().delete(meal);
    }

    @Override
    public void deleteById(int id){
        MapStorage.getInstance().deleteById(id);
    }
}

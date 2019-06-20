package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapStorage {
    private static MapStorage INSTANCE = new MapStorage();
    private ConcurrentHashMap<Integer, Meal> map;

    private MapStorage() {
        this.map = new ConcurrentHashMap<Integer, Meal>();
        createTestData();
    }

    public static MapStorage getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new MapStorage();
        }
        return INSTANCE;
    }

    public void add(Meal meal){
        map.put(meal.getId(), meal);
    }

    public Meal getById(int id){
        return map.getOrDefault(id, null);
    }

    public List<Meal> getAllMeals(){
        return new ArrayList<>(map.values());
    }

    public Set<Integer> getAllId(){
        return map.keySet();
    }

    public void update(Meal meal){
        map.put(meal.getId(), meal);
    }

    public void delete(Meal meal){
        map.remove(meal.getId());
    }

    public void deleteById(int id){
        map.remove(id);
    }

    private void createTestData(){
        map.put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        map.put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 14, 0), "Обед", 500));
        map.put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 22, 0), "Ужин", 500));
        map.put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        map.put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500));
        map.put(6, new Meal(6, LocalDateTime.of(2015, Month.MAY, 30, 21, 0), "Ужин", 1000));
        map.put(7, new Meal(7, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        map.put(8, new Meal(8, LocalDateTime.of(2015, Month.MAY, 11, 14, 0), "Обед", 600));
        map.put(9, new Meal(9, LocalDateTime.of(2015, Month.MAY, 10, 20, 0), "Ужин", 650));
        map.put(10, new Meal(10, LocalDateTime.of(2015, Month.MAY, 10, 8, 0), "Завтрак", 650));
    }
}

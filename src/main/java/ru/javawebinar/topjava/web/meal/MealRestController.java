package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class MealRestController {
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    // test for HW02
    public void sizeMeals(){
        System.out.println(service.getAll().size());
    }


    public List<MealTo> getAll(){
        return getWithExcess(service.getAll(), DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getMyAll(){
        return getWithExcess(service.getUserMeals(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getMyAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        return getWithExcess(
                service.getUserMealsFiltered(SecurityUtil.authUserId(), startDate, startTime, endDate, endTime),
                SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int id){
        return service.get(id, SecurityUtil.authUserId());
    }

    public void delete(int id){
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal save(Meal meal){
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id){
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.authUserId());
    }
}
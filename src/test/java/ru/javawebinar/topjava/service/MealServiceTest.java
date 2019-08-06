package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(MEAL_ID_U, USER_ID);
        MealTestData.assertMatch(meal, MEAL_TEST_U);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        mealService.get(MEAL_ID_U, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(MEAL_ID_U, USER_ID);
        MealTestData.assertNoContains(mealService.getAll(USER_ID), MEAL_TEST_U);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        mealService.delete(MEAL_ID_U, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> betweens = mealService.getBetweenDates(LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID);
        assertMatch(USERS_BETWEEN_MEAL, betweens);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> betweens = mealService.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 8, 0),
                LocalDateTime.of(2015, Month.MAY, 30, 23, 0), USER_ID);
        assertMatch(USERS_BETWEEN_MEAL, betweens);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = mealService.getAll(USER_ID);
        assertMatch(all, USERS_MEALS);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL_TEST_U);
        updated.setCalories(300);
        updated.setDescription("UpdateDescription");
        updated.setDateTime(LocalDateTime.of(2017, Month.JANUARY, 4, 6, 0));
        mealService.update(updated, USER_ID);
        assertMatch(mealService.get(MEAL_ID_U, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updatedNotFound() throws Exception{
        Meal updated = new Meal(MEAL_TEST_U);
        updated.setCalories(300);
        updated.setDescription("UpdateDescription");
        updated.setDateTime(LocalDateTime.of(2017, Month.JANUARY, 4, 6, 0));
        mealService.update(updated, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2016, Month.JUNE, 1, 14, 0),
                "Ланч", 560);
        Meal created = mealService.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MealTestData.assertContains(mealService.getAll(USER_ID), newMeal);
    }
}
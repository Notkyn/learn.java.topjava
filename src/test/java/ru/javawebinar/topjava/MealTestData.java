package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_U = 100005;
    public static final Meal MEAL_TEST_U;
    public static final List<Meal> USERS_MEALS = new ArrayList<>();
    public static final List<Meal> USERS_BETWEEN_MEAL = new ArrayList<>();

    static {
        Meal m09 = new Meal(START_SEQ + 9,
                LocalDateTime.of(2015, Month.MAY, 31, 20, 0),
                "Ужин", 510);
        Meal m08 = new Meal(START_SEQ + 8,
                LocalDateTime.of(2015, Month.MAY, 31, 13, 0),
                "Обед", 500);
        Meal m07 = new Meal(START_SEQ + 7,
                LocalDateTime.of(2015, Month.MAY, 10, 14, 0),
                "Завтрак", 1000);
        Meal m06 = new Meal(START_SEQ + 6,
                LocalDateTime.of(2015, Month.MAY, 30, 13, 0),
                "Обед", 1000);
        MEAL_TEST_U = new Meal(MEAL_ID_U,
                LocalDateTime.of(2015, Month.MAY, 30, 10, 0),
                "Завтрак", 500);
        Meal m04 = new Meal(START_SEQ + 4,
                LocalDateTime.of(2015, Month.MAY, 30, 20, 0),
                "Ужин", 500);

        Collections.addAll(USERS_MEALS, m04, m06, m07, m08, m09, MEAL_TEST_U);
        Collections.addAll(USERS_BETWEEN_MEAL, m06, m04, MEAL_TEST_U);
    }

    public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(List<Meal> actual, List<Meal> expected){
        assertContains(actual, expected);
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    public static void assertContains(Iterable<Meal> actual, Meal ... expected){
        assertContains(actual, Arrays.asList(expected));
    }

    private static void assertContains(Iterable<Meal> actual, Iterable<Meal> expected){
        for(Meal meal : expected){
            assertThat(actual).contains(meal);
        }
    }

    public static void assertNoContains(Iterable<Meal> actual, Meal expexted){
        assertThat(actual).doesNotContain(expexted);
    }

}

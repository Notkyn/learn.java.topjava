package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

public class MealsUtil {
    public static void main(String[] args) {

        List<MealTo> mealsWithExcess = getFilteredWithExcess(createData(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExcess.forEach(System.out::println);

        System.out.println(getFilteredWithExcessByCycle(createData(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println(getFilteredWithExcessInOnePass(createData(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println(getFilteredWithExcessInOnePass2(createData(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<MealTo> getFilteredWithExcess(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExcess(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealTo> getFilteredWithExcessByCycle(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<MealTo> mealsWithExcess = new ArrayList<>();
        meals.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealsWithExcess.add(createWithExcess(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExcess;
    }

    public static List<MealTo> getFilteredWithExcessInOnePass(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Collection<List<Meal>> list = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate)).values();

        return list.stream().flatMap(dayMeals -> {
            boolean excess = dayMeals.stream().mapToInt(Meal::getCalories).sum() > caloriesPerDay;
            return dayMeals.stream().filter(meal ->
                    TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                    .map(meal -> createWithExcess(meal, excess));
        }).collect(toList());
    }

    public static List<MealTo> getFilteredWithExcessInOnePass2(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final class Aggregate {
            private final List<Meal> dailyMeals = new ArrayList<>();
            private int dailySumOfCalories;

            private void accumulate(Meal meal) {
                dailySumOfCalories += meal.getCalories();
                if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                    dailyMeals.add(meal);
                }
            }

            // never invoked if the upstream is sequential
            private Aggregate combine(Aggregate that) {
                this.dailySumOfCalories += that.dailySumOfCalories;
                this.dailyMeals.addAll(that.dailyMeals);
                return this;
            }

            private Stream<MealTo> finisher() {
                final boolean excess = dailySumOfCalories > caloriesPerDay;
                return dailyMeals.stream().map(meal -> createWithExcess(meal, excess));
            }
        }

        Collection<Stream<MealTo>> values = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate,
                        Collector.of(Aggregate::new, Aggregate::accumulate, Aggregate::combine, Aggregate::finisher))
                ).values();

        return values.stream().flatMap(identity()).collect(toList());
    }

    private static MealTo createWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<Meal> createData(){
        return Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new Meal(LocalDateTime.of(2015, Month.MAY, 12, 9, 0), "Завтрак", 50),
                new Meal(LocalDateTime.of(2015, Month.MAY, 12, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 12, 20, 0), "Ужин", 100),
                new Meal(LocalDateTime.of(2015, Month.MAY, 11, 8, 0), "Завтрак", 600),
                new Meal(LocalDateTime.of(2015, Month.MAY, 11, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 11, 20, 9), "Ужин", 550),
                new Meal(LocalDateTime.of(2015, Month.MAY, 10, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 10, 13, 8), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 10, 20, 0), "Ужин", 650),
                new Meal(LocalDateTime.of(2015, Month.MAY, 5, 10, 10), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 5, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 5, 20, 0), "Ужин", 750),
                new Meal(LocalDateTime.of(2015, Month.MAY, 4, 10, 10), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 4, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 4, 20, 3), "Ужин", 850),
                new Meal(LocalDateTime.of(2015, Month.MAY, 3, 10, 15), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 3, 13, 10), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 3, 20, 7), "Ужин", 900),
                new Meal(LocalDateTime.of(2015, Month.MAY, 2, 10, 20), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 2, 13, 3), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 2, 20, 5), "Ужин", 800),
                new Meal(LocalDateTime.of(2015, Month.MAY, 1, 10, 10), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 1, 13, 23), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 1, 20, 0), "Ужин", 700)
        );
    }
}
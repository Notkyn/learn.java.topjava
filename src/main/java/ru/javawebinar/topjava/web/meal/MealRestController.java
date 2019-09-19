package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.util.LocalDateConvert;
import ru.javawebinar.topjava.web.util.LocalTimeConvert;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    final static String REST_URL = "/rest/meals";
    private LocalDateConvert dateConvert;
    private LocalTimeConvert timeConvert;

    @Autowired
    public void setTimeConvert(LocalTimeConvert timeConvert) {
        this.timeConvert = timeConvert;
    }

    @Autowired
    public void setDateConvert(LocalDateConvert dateConvert) {
        this.dateConvert = dateConvert;
    }

    @Override
    @GetMapping(value = "/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<Meal> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal mealCreate = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(mealCreate.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(mealCreate);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @GetMapping(value = "/filter")
    public List<MealTo> getBetween(@Nullable @RequestParam String startDate,
                                   @Nullable @RequestParam String startTime,
                                   @Nullable @RequestParam String endDate,
                                   @Nullable @RequestParam String endTime) {

        return super.getBetween(dateConvert.convert(startDate),
                timeConvert.convert(startTime),
                dateConvert.convert(endDate),
                timeConvert.convert(endTime));
    }
}
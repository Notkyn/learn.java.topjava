package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController  extends AbstractMealController{

    @GetMapping(value = "/meals")
    public String getAll(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @PostMapping(value = "/create")
    public String create(HttpServletRequest request) throws UnsupportedEncodingException {
        super.create(getMealFromForm(request));
        return "redirect:/meals";
    }

    @PostMapping(value = "/update")
    public String update(HttpServletRequest request) throws UnsupportedEncodingException {
        super.update(getMealFromForm(request), getId(request));
        return "redirect:meals";
    }

    @GetMapping(value = "/delete")
    public String delete(HttpServletRequest request){
        super.delete(getId(request));
        return "redirect:meals";
    }

    @GetMapping(value = "/createForm")
    public String createForm(Model model){
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        model.addAttribute("url", "create");
        return "mealForm";
    }

    @GetMapping(value = "/updateForm")
    public String updateForm(HttpServletRequest request, Model model){
        model.addAttribute("meal", super.get(getId(request)));
        model.addAttribute("url", "update");
        return "mealForm";
    }

    @GetMapping(value = "/filter")
    public String filter(HttpServletRequest request, Model model){
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        List<MealTo> mealsDateFiltered = super.getBetween(startDate, startTime, endDate, endTime);
        model.addAttribute("meals", mealsDateFiltered);
        return "redirect:meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private Meal getMealFromForm(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        return new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
    }
}

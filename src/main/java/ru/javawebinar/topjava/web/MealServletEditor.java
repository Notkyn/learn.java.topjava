package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.imp.MapDaoImp;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServletEditor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localId = req.getParameter("id");
        String action = "mealNew";
        String title = "Create Meal";

        if(localId != null && localId.length() > 0){
            title = "Update Meal";

            Dao dao = new MapDaoImp();
            Meal meal = dao.getById(Integer.parseInt(localId));

            req.setAttribute("id", localId);
            req.setAttribute("describe", meal.getDescription());
            req.setAttribute("calories", meal.getCalories());
            req.setAttribute("day", meal.getDate().getDayOfMonth());
            req.setAttribute("month", meal.getDate().getMonth().getValue());
            req.setAttribute("year", meal.getDate().getYear());
            req.setAttribute("hour", meal.getTime().getHour());
            req.setAttribute("minute", meal.getTime().getMinute());
            req.setAttribute("second", meal.getTime().getSecond());
        }

        req.setAttribute("action", action);
        req.setAttribute("title", title);

        req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
    }
}

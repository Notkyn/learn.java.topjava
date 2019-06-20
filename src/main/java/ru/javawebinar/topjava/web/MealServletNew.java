package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.imp.MapDaoImp;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.IdCounterUtil;
import ru.javawebinar.topjava.util.ParserUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServletNew extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String localId = req.getParameter("id");
        String describe = req.getParameter("describe");
        String calories = req.getParameter("calories");
        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        String hour = req.getParameter("hour");
        String minute = req.getParameter("minute");
        String second = req.getParameter("second");

        Dao dao = new MapDaoImp();
        int id;
        if(localId != null && localId.length() > 0){
            id = Integer.parseInt(localId);
            dao.deleteById(id);
        } else {
            id = IdCounterUtil.getNextId(dao.getAllId());
        }

        Meal meal = new Meal(id,
                LocalDateTime.of(ParserUtil.getYear(year),
                        ParserUtil.getMonthOrDay(month),
                        ParserUtil.getMonthOrDay(day),
                        ParserUtil.getInt(hour),
                        ParserUtil.getInt(minute),
                        ParserUtil.getInt(second)),
                describe,
                ParserUtil.getInt(calories));

        dao.add(meal);

        resp.sendRedirect("meals");
    }
}

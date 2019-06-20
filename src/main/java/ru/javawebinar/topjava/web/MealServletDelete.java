package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.imp.MapDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServletDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localId = req.getParameter("id");
        if(localId != null && localId.length() > 0){
            int id = Integer.parseInt(localId);
            Dao dao = new MapDaoImp();
            dao.deleteById(id);
        }
        resp.sendRedirect("meals");
    }
}

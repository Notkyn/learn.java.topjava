<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class="header">
    <div class="header_refresh_button">
        <a href="#">
            <div>
                <div class="refresh_button_img">
                    <img src="resources/images/icon-meal.png" alt="#">
                </div>
                <div class="refresh_button_text">
                    Подсчет калорий
                </div>
            </div>
        </a>
    </div>
    <div class="header_nav_panel">
        <div class="header_nav_button">RU</div>
        <div class="header_nav_button">
            <a href="index.html" class="nav_button_home">
                <div>
                    >>
                </div>
            </a>
        </div>
        <div class="header_nav_button">
            <a href="#" class="profile_button">
                <div>User профиль</div>
            </a>
        </div>
    </div>
</div>
<div class="content">
    <div class="content_container">
        <h2>Моя еда</h2>
        <input type="hidden" name="id" value="${user}">
        <div class="container_filter">
            <form method="get" action="meals?action=filter">
                <div class="container_value">
                    <div class="container_value_start_date">
                        <div>
                            <label for="startDate">От даты</label>
                            <input class="date" name="startDate" id="startDate" type="date">
                        </div>
                    </div>
                    <div class="container_value_end_date">
                        <div>
                            <label for="endDate">До даты</label>
                            <input class="date" name="endDate" id="endDate" type="date">
                        </div>
                    </div>
                    <div class="container_value_start_time">
                        <div>
                            <label for="startTime">От времени</label>
                            <input class="date" name="startTime" id="startTime" type="time">
                        </div>
                    </div>
                    <div class="container_value_end_time">
                        <div>
                            <label for="endTime">До времени</label>
                            <input class="date" name="endTime" id="endTime" type="time">
                        </div>
                    </div>
                </div>
                <div class="container_button">
                    <button type="button" class="filter_button_cancel"><a href="meals">Отменить</a></button>
                    <button class="filter_button_ok" type="submit">Отфильтровать</button>
                </div>
            </form>
        </div>
        <div class="add_button_container">
            <a href="#" class="add_button">
                <div>+ Добавить</div>
            </a>
        </div>
        <table class="table_meal">
            <thead>
            <tr>
                <th>Дата/Время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr class="${meal.excess ? 'excess' : 'normal'}">
                    <td class="table_date">${fn:formatDateTime(meal.dateTime)}</td>
                    <td class="table_describe">${meal.description}</td>
                    <td class="table_calories">${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">U</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">D</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

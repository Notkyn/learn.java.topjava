<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
    <link rel="stylesheet" href="resources/css/style.css">
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
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

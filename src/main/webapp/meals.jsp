<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
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
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <div class="container_filter">
        <form method="get" action="meals">
            <input type="hidden" name="action" value="filter">
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
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
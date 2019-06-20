<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<hr>
<a href="mealsEditor">Добавить</a>
<hr>
<table>
    <tr>
        <td>Id</td>
        <td>Description</td>
        <td>Date</td>
        <td>Calories</td>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <c:if test="${meal.excess == true}">
            <tr style="background: red">
                <td>${meal.id}</td>
                <td>${meal.description}</td>
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
                </td>
                <td>${meal.calories}</td>
                <td><a href="mealsEditor?id=${meal.id}">Редактировать</a></td>
                <td><a href="mealDelete?id=${meal.id}">Удалить</a></td>
            </tr>
        </c:if>
        <c:if test="${meal.excess == false}">
            <tr style="background: green">
                <td>${meal.id}</td>
                <td>${meal.description}</td>
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
                </td>
                <td>${meal.calories}</td>
                <td><a href="mealsEditor?id=${meal.id}">Редактировать</a></td>
                <td><a href="mealDelete?id=${meal.id}">Удалить</a></td>
            </tr>
        </c:if>
    </c:forEach>
</table>


</body>
</html>

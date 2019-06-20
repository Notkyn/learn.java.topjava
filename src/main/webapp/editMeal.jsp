<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editors</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${title}</h2>
<form method="get" action="${action}">
    <label><input style="display: none" type="text" name="id" value="${id}"></label>
    <table>
        <tr>
            <td>Описание:</td>
            <td><label><input type="text" name="describe" value="${describe}"></label></td>
        </tr>
        <tr>
            <td>Калории:</td>
            <td><label><input type="text" name="calories" value="${calories}"></label></td>
        </tr>
        <tr>
            <td>Дата</td>
        </tr>
        <tr>
            <td>День:</td>
            <td><td><label><input type="text" name="day" value="${day}"></label></td>
        </tr>
        <tr>
            <td>Месяц:</td>
            <td><td><label><input type="text" name="month" value="${month}"></label></td>
        </tr>
        <tr>
            <td>Год:</td>
            <td><td><label><input type="text" name="year" value="${year}"></label></td>
        </tr>
        <tr>
            <td>Время</td>
        </tr>
        <tr>
            <td>Час:</td>
            <td><td><label><input type="text" name="hour" value="${hour}"></label></td>
        </tr>
        <tr>
            <td>Минуты:</td>
            <td><td><label><input type="text" name="minute" value="${minute}"></label></td>
        </tr>
        <tr>
            <td>Секунды:</td>
            <td><td><label><input type="text" name="second" value="${second}"></label></td>
        </tr>
    </table>
    <hr>
    <label><input type="submit"></label>
</form>
</body>
</html>

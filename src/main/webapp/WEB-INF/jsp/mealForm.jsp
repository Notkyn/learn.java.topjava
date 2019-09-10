<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><a href="index.jsp"><spring:message code="app.home"/></a></h3>
    <hr>
    <c:choose>
        <c:when test="${url == 'create'}">
            <h2><spring:message code="mealForm.create"/></h2>
        </c:when>
        <c:otherwise>
            <h2><spring:message code="mealForm.edit"/></h2>
        </c:otherwise>
    </c:choose>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="${url}">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt><spring:message code="mealForm.date"/></dt>
            <dd><label><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></label></dd>
        </dl>
        <dl>
            <dt><spring:message code="mealForm.description"/></dt>
            <dd><label><input type="text" value="${meal.description}" size=40 name="description" required></label></dd>
        </dl>
        <dl>
            <dt><spring:message code="mealForm.calories"/></dt>
            <dd><label><input type="number" value="${meal.calories}" name="calories" required></label></dd>
        </dl>
        <button type="submit"><spring:message code="mealForm.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="mealForm.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

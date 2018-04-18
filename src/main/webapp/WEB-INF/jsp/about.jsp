<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>О магазине</title>
    <link rel="stylesheet" href="<spring:url value="/resources/stylesheet/style.css"/>">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/top.jsp"/>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/static/left_side.jsp"/>
    <div class="content">
        <h2 style="text-align: center">какая-то информация</h2>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
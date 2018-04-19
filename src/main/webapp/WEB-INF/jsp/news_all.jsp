<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="news" scope="request" type="java.util.List"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
    <link rel="stylesheet" href="<spring:url value="/resources/stylesheet/style.css"/>">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/top.jsp"/>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/static/left_side.jsp"/>
    <div class="content">
        <jsp:include page="/WEB-INF/jsp/static/most_rated_products.jsp"/>
        <c:if test="${!news.isEmpty()}">
            <c:forEach items="${news}" var="item">
                <spring:url value="/news/{news_id}" var="url">
                    <spring:param name="news_id" value="${item.id}"/>
                </spring:url>
                <div class="block">
                    <table class="news-preview">
                        <tr>
                            <td class="news-preview-header"><a href="${url}">${item.name}</a></td>
                        </tr>
                        <tr>
                            <td class="news-preview-body">${item.text}</td>
                        </tr>
                    </table>
                </div>
                <br>
            </c:forEach>
        </c:if>
        <c:if test="${news.isEmpty()}">
            <span>Данные отсутствуют</span>
        </c:if>
        <jsp:include page="/WEB-INF/jsp/static/pagination.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
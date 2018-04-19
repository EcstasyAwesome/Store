<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="news" scope="request" type="com.github.company.dao.entity.News"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${news.name}</title>
    <link rel="stylesheet" href="<spring:url value="/resources/stylesheet/style.css"/>">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/top.jsp"/>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/static/left_side.jsp"/>
    <div class="content">
        <c:if test="${news!=null}">
            <div class="block">
                <table class="news">
                    <tr>
                        <td class="news-header">${news.name}</td>
                    </tr>
                    <c:if test="${news.image!=null}">
                        <tr>
                            <td class="news-image">
                                <img src="<spring:url value="${news.image}"/>"/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="news-body">${news.text}</td>
                    </tr>
                    <tr>
                        <td class="news-footer">
                            Опубликовано: <fmt:formatDate pattern="dd.MM.yyyy kk:mm:ss" value="${news.date}"/>
                        </td>
                    </tr>
                </table>
            </div>
            <br>
        </c:if>
        <c:if test="${news==null}">
            <span>Данные отсутствуют</span>
        </c:if>
        <jsp:include page="/WEB-INF/jsp/static/comments.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="categories" scope="request" type="java.util.List"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Категории</title>
    <link rel="stylesheet" href="<spring:url value="/resources/stylesheet/style.css"/>">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/top.jsp"/>
<div class="container" style="text-align: center">
    <jsp:include page="/WEB-INF/jsp/static/left_side.jsp"/>
    <div class="content">
        <div class="block-functions">
            <a href="<spring:url value="/category/add"/>">Добавить</a>
        </div>
        <c:if test="${!categories.isEmpty()}">
            <c:forEach items="${categories}" var="category">
                <spring:url value="/category/{id}" var="url">
                    <spring:param name="id" value="${category.id}"/>
                </spring:url>
                <div class="block-inline">
                    <table class="table-preview">
                        <tr>
                            <td class="table-row-preview-image"><a href="${url}"><img src="${category.image}"></a></td>
                        </tr>
                        <tr>
                            <td class="table-row-preview-text"><a href="${url}">${category.name}</a></td>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${categories.isEmpty()}">
            <span style="text-align: center">Данные отсутствуют</span>
        </c:if>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
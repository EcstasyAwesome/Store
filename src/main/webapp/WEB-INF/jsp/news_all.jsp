<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="news" scope="request" type="java.util.List"/>
<jsp:useBean id="products" scope="request" type="java.util.List"/>
<jsp:useBean id="currentPage" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="noOfPages" scope="request" type="java.lang.Integer"/>
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
        <c:if test="${!products.isEmpty()}">
            <c:forEach items="${products}" var="item">
                <spring:url value="/category/{category_id}/product/{product_id}" var="url">
                    <spring:param name="category_id" value="${item.category.id}"/>
                    <spring:param name="product_id" value="${item.id}"/>
                </spring:url>
                <spring:url value="${item.image}" var="image"/>
                <div class="block-inline">
                    <table>
                        <tr>
                            <td class="product-rated-name"><a href="${url}">${item.name}</a></td>
                        </tr>
                        <tr>
                            <td class="product-rated-image">
                                <a href="${url}"><img src="${image}" width="150"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td>₴ ${item.price}</td>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${products.isEmpty()}">
            <span>Данные отсутствуют</span>
        </c:if>
        <hr style="border: 1px solid #eee">
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
        <%--pagination--%>
        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <c:url value="/" var="url">
                    <c:param name="page" value="${currentPage - 1}"/>
                </c:url>
                <a href="${url}"><<<</a>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a class="current-link">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/" var="url">
                            <c:param name="page" value="${i}"/>
                        </c:url>
                        <a href="${url}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <c:url value="/" var="url">
                    <c:param name="page" value="${currentPage + 1}"/>
                </c:url>
                <a href="${url}">>>></a>
            </c:if>
        </div>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
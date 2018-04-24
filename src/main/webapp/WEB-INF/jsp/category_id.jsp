<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="products" scope="request" type="java.util.List"/>
<jsp:useBean id="category" scope="request" type="com.github.company.dao.entity.Category"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${category.name}</title>
    <link rel="stylesheet" href="<spring:url value="/resources/stylesheet/style.css"/>">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/top.jsp"/>
<div class="container" style="text-align: center">
    <jsp:include page="/WEB-INF/jsp/static/left_side.jsp"/>
    <div class="content">
        <div class="block-functions">
            <spring:url value="/category/{category_id}/add" var="add">
                <spring:param name="category_id" value="${category.id}"/>
            </spring:url>
            <a href="${add}">Добавить</a>
        </div>
        <c:if test="${!products.isEmpty()}">
            <c:forEach items="${products}" var="product">
                <spring:url value="/category/{category_id}/product/{product_id}" var="url">
                    <spring:param name="category_id" value="${product.category.id}"/>
                    <spring:param name="product_id" value="${product.id}"/>
                </spring:url>
                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₴" maxFractionDigits="0"
                                  var="price"/>
                <div class="block-inline">
                    <table class="table-preview">
                        <tr>
                            <td class="table-row-preview-image"><a href="${url}"><img src="${product.image}"></a></td>
                        </tr>
                        <tr>
                            <td class="table-row-preview-text"><a href="${url}">${product.name}</a></td>
                        </tr>
                        <tr>
                            <td class="table-row-preview-text"><a href="${url}">${price}</a></td>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${products.isEmpty()}">
            <span style="text-align: center">Данные отсутствуют</span>
        </c:if>
        <jsp:include page="/WEB-INF/jsp/static/pagination.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
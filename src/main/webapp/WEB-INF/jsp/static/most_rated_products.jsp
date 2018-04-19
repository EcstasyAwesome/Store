<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="products" scope="request" type="java.util.List"/>
<c:if test="${!products.isEmpty()}">
    <c:forEach items="${products}" var="item">
        <spring:url value="/category/{category_id}/product/{product_id}" var="url">
            <spring:param name="category_id" value="${item.category.id}"/>
            <spring:param name="product_id" value="${item.id}"/>
        </spring:url>
        <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="₴" maxFractionDigits="0"
                          var="price"/>
        <div class="block-inline">
            <table>
                <tr>
                    <td class="product-rated-name"><a href="${url}">${item.name}</a></td>
                </tr>
                <tr>
                    <td class="product-rated-image">
                        <a href="${url}"><img src="<spring:url value="${item.image}"/>" width="150"/></a>
                    </td>
                </tr>
                <tr>
                    <td>${price}</td>
                </tr>
            </table>
        </div>
    </c:forEach>
</c:if>
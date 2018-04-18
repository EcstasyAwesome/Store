<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="categories" scope="request" type="java.util.List"/>
<div class="left-side">
    <div class="block">
        <table class="box">
            <tr>
                <td class="box-header">Категории</td>
            </tr>
            <tr>
                <td class="box-body">
                    <c:if test="${!categories.isEmpty()}">
                        <c:forEach items="${categories}" var="category">
                            <spring:url value="/category/{id}" var="url">
                                <spring:param name="id" value="${category.id}"/>
                            </spring:url>
                            <a href="<spring:url value="${url}"/>">${category.name}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${categories.isEmpty()}">
                        <span>Данные отсутствуют</span>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
</div>
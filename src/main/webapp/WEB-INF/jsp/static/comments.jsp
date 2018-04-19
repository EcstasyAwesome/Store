<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="comments" scope="request" type="java.util.List"/>
<c:if test="${!comments.isEmpty()}">
    <c:forEach items="${comments}" var="item">
        <div class="block">
            <table class="comment">
                <tr>
                    <td class="comment-header">${item.user.firstName} ${item.user.lastName} -
                        <fmt:formatDate pattern="dd.MM.yyyy kk:mm:ss" value="${item.date}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="comment-body">${item.text}</td>
                </tr>
            </table>
        </div>
        <br>
    </c:forEach>
</c:if>
<c:if test="${comments.isEmpty()}">
    <span>Нет комментариев</span>
</c:if>
<div class="block">
    <%--@elvariable id="comment" type="com.github.company.dao.entity.NewsComment"--%>
    <spring:form method="post" modelAttribute="comment">
        <table align="center">
            <tr>
                <td><spring:textarea path="text" cols="85" rows="5"/></td>
                <td><spring:errors path="text"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Комментировать"/></td>
            </tr>
        </table>
    </spring:form>
</div>
<jsp:include page="/WEB-INF/jsp/static/pagination.jsp"/>
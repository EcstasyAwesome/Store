<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="comments" scope="request" type="java.util.List"/>
<c:if test="${!comments.isEmpty()}">
    <c:forEach items="${comments}" var="item">
        <form:form method="delete">
            <input type="hidden" name="comment_id" value="${item.id}"/>
            <div class="block">
                <table class="comment">
                    <tr>
                        <td class="comment-header-left">${item.user.firstName} ${item.user.lastName} -
                            <fmt:formatDate pattern="dd.MM.yyyy kk:mm:ss" value="${item.date}"/>
                        </td>
                        <td class="comment-header-right">
                            <input type="submit" value="удалить комментарий">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="comment-body">${item.text}</td>
                    </tr>
                </table>
            </div>
            <br>
        </form:form>
    </c:forEach>
</c:if>
<c:if test="${comments.isEmpty()}">
    <span>Нет комментариев</span>
</c:if>
<div class="block">
    <%--@elvariable id="comment" type="com.github.company.dao.entity.NewsComment"--%>
    <form:form method="post" modelAttribute="comment">
        <table align="center">
            <tr>
                <td><form:textarea path="text" cols="85" rows="5"/></td>
            </tr>
            <spring:bind path="text">
                <c:if test="${status.error}">
                    <tr>
                        <td class="error-message"><form:errors path="text"/></td>
                    </tr>
                </c:if>
            </spring:bind>
            <tr>
                <td><input type="submit" value="Комментировать"/></td>
            </tr>
        </table>
    </form:form>
</div>
<jsp:include page="/WEB-INF/jsp/static/pagination.jsp"/>
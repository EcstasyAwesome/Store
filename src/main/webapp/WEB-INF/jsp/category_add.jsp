<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новая категория</title>
    <link rel="stylesheet" href="<spring:url value="/resources/stylesheet/style.css"/>">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/top.jsp"/>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/static/left_side.jsp"/>
    <div class="content">
        <%--@elvariable id="category" type="com.github.company.dao.entity.Category"--%>
        <form:form method="post" enctype="multipart/form-data" modelAttribute="category">
            <table align="center">
                <tr>
                    <td width="200" class="table-top">Категория</td>
                    <td width="400" class="table-main"><form:input size="50" path="name"/></td>
                </tr>
                <tr>
                    <td class="table-top">Изображение</td>
                    <td class="table-main"><input type="file" name="file" accept="image/jpeg,image/png,image/gif"></td>
                </tr>
                <spring:bind path="*">
                    <c:if test="${status.error}">
                        <tr>
                            <td colspan="2" class="error-message">${status.errorCode}</td>
                        </tr>
                    </c:if>
                </spring:bind>
                <tr>
                    <td colspan="2"><br><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form:form>
    </div>
    <jsp:include page="/WEB-INF/jsp/static/right_side.jsp"/>
</div>
<jsp:include page="/WEB-INF/jsp/static/bottom.jsp"/>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<%--@elvariable id="wish" type="com.github.company.dao.User"--%>
<form:form modelAttribute="wish">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td>Admin:</td>
            <td><form:checkbox path="admin"/></td>
        </tr>
        <tr>
            <td>Interests:</td>
            <td><form:select path="interests" items="${wish.interests}"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Save Changes"/>
            </td>
        </tr>
    </table>
</form:form>
<img src="<c:url value="/resources/ecstasy_logo.jpg" />">
</body>
</html>
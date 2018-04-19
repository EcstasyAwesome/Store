<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="currentPage" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="numberOfPages" scope="request" type="java.lang.Integer"/>
<c:set var="currentURL" scope="page" value="${requestScope['javax.servlet.forward.servlet_path']}"/>
<c:if test="${numberOfPages > 1}">
    <div class="pagination">
        <c:if test="${currentPage != 1}">
            <c:url value="${currentURL}" var="url">
                <c:param name="page" value="${currentPage - 1}"/>
            </c:url>
            <a href="${url}"><<<</a>
        </c:if>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <a class="current-link">${i}</a>
                </c:when>
                <c:otherwise>
                    <c:url value="${currentURL}" var="url">
                        <c:param name="page" value="${i}"/>
                    </c:url>
                    <a href="${url}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt numberOfPages}">
            <c:url value="${currentURL}" var="url">
                <c:param name="page" value="${currentPage + 1}"/>
            </c:url>
            <a href="${url}">>>></a>
        </c:if>
    </div>
</c:if>
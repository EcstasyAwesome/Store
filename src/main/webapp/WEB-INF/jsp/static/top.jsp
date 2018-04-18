<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="header">
    <img src="<spring:url value="/resources/img/ecstasy_logo.jpg"/>" alt="Логотип">
</div>
<div class="nav">
    <div class="nav-left">
        <a href="<spring:url value="/"/>">Главная</a>
        <a href="<spring:url value="/category"/>">Категории</a>
        <a href="<spring:url value="/about"/>">О компании</a>
    </div>
    <div class="nav-right">
        <a href="#">Вход</a>
        <a href="#">Регистрация</a>
    </div>
</div>
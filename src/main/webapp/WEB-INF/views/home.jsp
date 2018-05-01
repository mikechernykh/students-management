<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <title><spring:message code="homePage.title"/></title>
</head>
<body>
<%@ include file="portion/header.jsp" %>
<div class="container">
    <h3 class="h3"><spring:message code="homePage.title"/></h3>
    <p>Реализовать систему ввода и отображения информации о студентах института, включающую следующие сущности и их атрибуты:</p>
    <ul>
        <li>Студент</li>
        <li>Имя</li>
        <li>Фамилия</li>
        <li>Отчество</li>
        <li>Дата рождения</li>
    </ul>
    <p>Группа:</p>
    <ul>
        <li>Наименование</li>
        <li>Номер</li>
        <li>Название факультета</li>
    </ul>
    <p>Система должна иметь следующие функции:</p>
    <ul>
        <li>Отображение списка групп</li>
        <li>Добавление новой группы, редактирование и удаление существующей</li>
        <li>Отображения списка студентов</li>
        <li>Фильтрация списка студентов по фамилии и по номеру группы</li>
        <li>Добавление нового студента, редактирование и удаление существующего</li>
    </ul>
</div>
<%@ include file="portion/footer.jsp" %>
</body>
</html>
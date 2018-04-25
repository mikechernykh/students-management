<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <title>
        <spring:message code="viewStudentPage.title"/>
    </title>
</head>
<body>
<%@ include file="../portion/header.jsp" %>
<div class="container">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/groups">
                    <spring:message code="breadcrumb.students"/>
                </a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">${student.lastNameInitials()}</li>
        </ol>
    </nav>
    <div class="row">
        <h3 class="h3">
            <spring:message code="viewStudentPage.title"/>
        </h3>
        <table class="table table-striped">
            <tbody>
            <tr>
                <th scope="col">
                    <spring:message code="studentsPage.tableColumn.id"/>
                </th>
                <td scope="col">${student.id}</td>
            </tr>
            <tr>
                <th scope="col">
                    <spring:message code="studentsPage.tableColumn.lastName"/>
                </th>
                <td>${student.lastName}</td>
            </tr>
            <tr>
                <th scope="col">
                    <spring:message code="studentsPage.tableColumn.firstName"/>
                </th>
                <td>${student.firstName}</td>
            </tr>
            <tr>
                <th scope="col">
                    <spring:message code="studentsPage.tableColumn.middleName"/>
                </th>
                <td>${student.middleName}</td>
            </tr>
            <tr>
                <th scope="col">
                    <spring:message code="studentsPage.tableColumn.dateOfBirth"/>
                </th>
                <td>${student.dateOfBirth}</td>
            </tr>
            <tr>
                <th scope="col">
                    <spring:message code="studentsPage.tableColumn.group"/>
                </th>
                <td><a href="/groups/${student.group.id}"/>${student.group.name}</a></td>
            </tr>
            </tbody>
        </table>
        <span>
            <a href="/students/${student.id}/edit" class="btn btn-secondary">
                <spring:message code="viewStudentPage.btnEdit"/>
            </a>
        </span>
        <span>
            <a href="/students/${student.id}/delete" class="btn btn-danger">
                <spring:message code="viewStudentPage.btnDelete"/>
            </a>
        </span>
    </div>
</div>
<%@ include file="../portion/footer.jsp"%>
</body>
</html>
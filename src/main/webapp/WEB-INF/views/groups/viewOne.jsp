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
        <spring:message code="viewGroupPage.title"/>
    </title>
</head>
<body>
<%@ include file="../portion/header.jsp" %>
<div class="container">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/groups">
                    <spring:message code="breadcrumb.groups"/>
                </a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">${group.name}</li>
        </ol>
    </nav>
    <div class="row">
        <div class="col-md-6">
            <h3 class="h3">
                <spring:message code="viewGroupPage.title"/>
            </h3>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <th scope="col">
                        <spring:message code="groupsPage.tableColumn.id"/>
                    </th>
                    <td scope="col">${group.id}</td>
                </tr>
                <tr>
                    <th scope="col">
                        <spring:message code="groupsPage.tableColumn.name"/>
                    </th>
                    <td>${group.name}</td>
                </tr>
                <tr>
                    <th scope="col">
                        <spring:message code="groupsPage.tableColumn.faculty"/>
                    </th>
                    <td>${group.facultyName}</td>
                </tr>
                </tbody>
            </table>
            <span>
                <a href="/groups/${group.id}/edit" class="btn btn-secondary">
                    <spring:message code="viewGroupPage.btnEdit"/>
                </a>
            </span>
            <span>
                <a href="/groups/${group.id}/delete" class="btn btn-danger">
                    <spring:message code="viewGroupPage.btnDelete"/>
                </a>
            </span>
        </div>
        <div class="col-md-6">
            <h3 class="h3">
                <spring:message code="viewGroupPage.students"/>
            </h3>
            <ul class="list-group">
                <c:forEach var="student" items="${studentList}">
                    <li class="list-group-item">
                        <a href="/students/${student.id}">${student.lastName} ${student.firstName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@ include file="../portion/footer.jsp"%>
</body>
</html>
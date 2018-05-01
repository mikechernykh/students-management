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
        <spring:message code="groupsPage.title"/>
    </title>
</head>
<body>
<%@ include file="../portion/header.jsp" %>
<div class="container">

    <div class="mt-3 mb-3">
        <a href="/groups/create" class="btn btn-primary">
            <spring:message code="groupsPage.addLink"/>
        </a>
    </div>
    <h3 class="h3">
        <spring:message code="groupsPage.title"/>
    </h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">
                <spring:message code="groupsPage.tableColumn.id"/>
            </th>
            <th scope="col">
                <spring:message code="groupsPage.tableColumn.name"/>
            </th>
            <th scope="col">
                <spring:message code="groupsPage.tableColumn.faculty"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="group" items="${pageImpl.content}">
            <tr>
                <td scope="col"><a href="/groups/${group.id}">${group.id}</a></td>
                <td>${group.name}</td>
                <td>${group.facultyName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <jsp:include page="../portion/pagination.jsp"/>
</div>

<%@ include file="../portion/footer.jsp" %>
</body>
</html>
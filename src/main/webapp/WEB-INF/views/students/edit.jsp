<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <spring:message code="editStudentPage.title"/>
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
            <li class="breadcrumb-item">
                <a href="/students/${student.id}">${student.lastNameInitials()}</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">
                <spring:message code="breadcrumb.students.edit"/>
            </li>
        </ol>
    </nav>
    <h3 class="h3">
        <spring:message code="editStudentPage.title"/>
    </h3>

    <jsp:include page="form.jsp"/>

</div>

<%@ include file="../portion/footer.jsp"%>
</body>
</html>
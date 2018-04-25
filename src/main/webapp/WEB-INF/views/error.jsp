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

    <title><spring:message code="errorPage.title"/></title>
</head>
<body>
<%@ include file="portion/header.jsp" %>
<div class="container text-center">
    <p class="code-404 text-danger font-weight-bold">404</p>
    <p class="mt-3 text-primary"><spring:message code="${message}"/></p>
    <a href="/" class="btn btn-danger"><spring:message code="error.btnHome"/></a>
</div>

<%@ include file="portion/footer.jsp" %>
</body>
</html>
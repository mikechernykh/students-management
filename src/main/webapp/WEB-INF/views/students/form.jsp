<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="studentDto" method="post">
    <form:hidden path="id"/>
    <div class="form-group">
        <form:label path="lastName">
            <spring:message code="studentFormPage.lastName"/>
        </form:label>
        <form:input path="lastName" class="form-control"/>
        <form:errors path="lastName" cssClass="text-danger"/>
    </div>
    <div class="form-group">
        <form:label path="firstName">
            <spring:message code="studentFormPage.firstName"/>
        </form:label>
        <form:input path="firstName" class="form-control"/>
        <form:errors path="firstName" cssClass="text-danger"/>
    </div>
    <div class="form-group">
        <form:label path="middleName">
            <spring:message code="studentFormPage.middleName"/>
        </form:label>
        <form:input path="middleName" class="form-control"/>
        <form:errors path="middleName" cssClass="text-danger"/>
    </div>
    <div class="form-group">
        <form:label path="dateOfBirth">
            <spring:message code="studentFormPage.dateOfBirth"/>
        </form:label>
        <form:input path="dateOfBirth" class="form-control"/>
        <form:errors path="dateOfBirth" cssClass="text-danger"/>
    </div>
    <div class="form-group">
        <form:label path="groupName">
            <spring:message code="studentFormPage.group"/>
        </form:label>
        <form:select class="form-control" path="groupName">
            <form:option value="">--- Select group ---</form:option>
            <form:options items="${groupNames}"/>
        </form:select>
        <form:errors path="groupName" cssClass="text-danger"/>
    </div>
    <button class="btn btn-primary">
        <spring:message code="studentFormPage.btnSave"/>
    </button>
</form:form>
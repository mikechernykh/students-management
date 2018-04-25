<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="groupDto" method="post">
    <form:hidden path="id"/>
    <div class="form-group">
        <form:label path="name">
            <spring:message code="createGroupPage.groupName"/>
        </form:label>
        <form:input path="name" class="form-control"/>
        <form:errors path="name" cssClass="text-danger"/>
    </div>

    <div class="form-group">
        <form:label path="facultyName">
            <spring:message code="createGroupPage.groupFacultyName"/>
        </form:label>
        <form:input path="facultyName" class="form-control"/>
        <form:errors path="facultyName" cssClass="text-danger"/>
    </div>
    <button class="btn btn-primary">
        <spring:message code="editGroupPage.btnSave"/>
    </button>
</form:form>
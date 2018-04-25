<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="header">
    <div class="container">
        <div class="clearfix">
            <div class="float-left">
                <nav>
                    <a class="btn" href="/groups">
                        <spring:message code="nav.groupsItem"/>
                    </a>
                    <a class="btn" href="/students">
                        <spring:message code="nav.studentsItem"/>
                    </a>
                </nav>
                <!--<ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active" href="/groups">
                            <spring:message code="nav.groupsItem"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/students">
                            <spring:message code="nav.studentsItem"/>
                        </a>
                    </li>
                </ul>-->
            </div>
            <div class="float-right">
                <span class="btn dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown"
                      aria-haspopup="true" aria-expanded="false">
                    <spring:message code="app.locale"/>
                </span>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="?locale=en">en</a>
                    <a class="dropdown-divider" href="#"></a>
                    <a class="dropdown-item" href="?locale=ru">ru</a>
                </div>
            </div>
        </div>
    </div>
</div>
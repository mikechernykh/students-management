<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${pageImpl.totalPages gt 1}">
    <nav>
        <ul class="pagination">
            <c:forEach var="page" begin="0" step="1" end="${pageImpl.totalPages - 1}">
                <c:choose>
                    <c:when test="${pageImpl.number eq page}">
                        <li class="page-item active">
                            <a href="?page=${page}&size=${pageImpl.size}" class="page-link">${page + 1}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a href="?page=${page}&size=${pageImpl.size}" class="page-link">${page + 1}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </nav>
</c:if>
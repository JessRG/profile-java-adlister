<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: trevaconda--%>
<%--  Date: 10/1/20--%>
<%--  Time: 12:26 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Ad view!" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="panel panel-info wid">
    <div class="panel-heading">
        <h1 class="panel-title" >  <c:out value="${ad.title}"/></h1>
    </div>
    <div class="panel-body">
        <p> <c:out value="${ad.description}"/></p>
        <div>
            <strong>Contact: </strong>
            <c:out value="${user.email}"/>
            <br>
            <%--    Only display categories list if list is not empty--%>
            <c:if test="${!cat.isEmpty()}">
                <strong>Categories: </strong>
                <ul>
                    <c:forEach var="cat" items="${categories}">
                        <li>${cat.name}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
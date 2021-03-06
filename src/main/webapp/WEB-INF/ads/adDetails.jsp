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
        <h1 class="panel-title" >  <c:out value="${ad.title}"/> </h1>
    </div>
    <div class="panel-body">
        <p> <c:out value="${ad.description}"/></p>
        <div>
            <strong>Contact: </strong>
            <c:out value="${user.email}"/>
            <br>
            <%--    Only display categories list if list is not empty--%>
            <c:if test="${!categoriesList.isEmpty()}">
                <strong>Categories: </strong>
                <ul>
                    <c:forEach var="cat" items="${categoriesList}">
                        <li>${cat.name}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
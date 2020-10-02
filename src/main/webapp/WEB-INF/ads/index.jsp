<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<jsp:include page="../partials/messages.jsp"/>
<div class="container">
    <h1>Here Are all the ads!</h1>

    <form role="search" action="/ads" class="form-inline my-2 my-lg-0">
        <input id="search" name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Find Ads</button>
    </form>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <a href="/details?adId=${ad.id}&userId=${ad.userId}">${ad.title}</a>
        </div>
    </c:forEach>
</div>

</body>
</html>

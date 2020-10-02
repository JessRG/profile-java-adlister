<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Edit User"/>
    </jsp:include>
</head>
<jsp:include page="../partials/navbar.jsp"/>

<form action="/update?adId=${adId}" method="post">
    <div class="d-flex align-items-baseline">
        <label for="new_title"> Title:</label>
        <input type="text" name="new_title" id="new_title"/>
        <label for="new_description"> Description:</label>
        <textarea type="text" name="new_description" id="new_description"></textarea>
        <button type="submit"> Done! </button>
    </div>
</form>
</html>

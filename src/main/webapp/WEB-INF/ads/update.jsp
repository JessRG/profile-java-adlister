<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Edit User"/>
    </jsp:include>
</head>
<jsp:include page="../partials/navbar.jsp"/>

<form action="/update" method="post">
    <label for="new_title"> New Username: </label>
    <input type="text" name="new_title" id="new_title"/>
    <label for="new_description"> New Password: </label>
    <input type="text" name="new_description" id="new_description"/>
    <button type="submit"> Done! </button>
</form>
</html>

<%--
  Created by IntelliJ IDEA.
  User: trevaconda
  Date: 10/1/20
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit User"/>
    </jsp:include>
</head>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<form action="/profile/edit" method="post">
    <label for="new_username"> New Username: </label>
    <input type="text" name="new_username" id="new_username"/>
    <label for="new_password"> New Password: </label>
    <input type="text" name="new_password" id="new_password"/>
    <label for="confirm_new_password"> Confirm New Password: </label>
    <input type="text" name="new_password" id="confirm_new_password"/>
    <label for="new_email"> New Email: </label>
    <input type="email" id="new_email" name="new_email">
    <button type="submit"> Done! </button>
</form>
</html>

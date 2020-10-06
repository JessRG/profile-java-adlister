<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Megalist</a>
        </div>

        <ul class="nav navbar-nav navbar-right align-item-center">
            <c:choose>
                <c:when test="${user == null}">
                    <li class="nav-item"><a href="/login">Login</a></li>
                    <li class="nav-item"><a href="/register">Register</a></li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a href="/ads">View All Ads</a></li>
                    <li class="nav-item"><a href="/ads/create">Create New Ad</a></li>
                    <li class="nav-item"><a href="/profile">Profile</a></li>
                    <li class="nav-item"><a href="/logout">Logout</a></li>
                    <li style="font-size:2em" class="nav-item" value="Update Profile"><a href="/update_user">&#9881;</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
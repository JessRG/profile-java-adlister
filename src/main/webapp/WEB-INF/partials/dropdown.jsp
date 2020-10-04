<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="dropdown">
    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Dropdown
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
        <form method="GET" action="/ads">
            <c:forEach var="cat" items="${allCategories}">
                <button class="dropdown-item" type="submit" name="category" value="${cat.id}">${cat.name}</button>
            </c:forEach>
        </form>
    </div>
</div>

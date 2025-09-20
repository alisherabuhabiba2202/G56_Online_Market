
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="../css/css.jsp"/>
</head>
<body>
<jsp:include page="../fragments/NavbarHomePage.jsp"/>
<br>
<div class="row">
    <c:forEach items="${products}" var="product">
        <div class="col-md-4">
            <div class="card" style="width: 18rem;">
                <img src="..." class="card-img-top" alt="product.png">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text">Narx : ${product.price}</p>
                    <a href="/productInfo?id=${product.id}" class="btn btn-primary">More...</a>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

<jsp:include page="../js/js.jsp"/>
</body>
</html>

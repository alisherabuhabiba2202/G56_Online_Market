<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <jsp:include page="/css/css.jsp"/>
</head>
<body>

<jsp:include page="/fragments/NavbarHomePage.jsp"/>

<div class="container mt-4">
    <div class="row g-3">
        <c:forEach var="product" items="${products}">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="card h-100 text-center">
                    <img src="${pageContext.request.contextPath}/files/images/product_img/${product.filePath}"
                         class="card-img-top img-fluid" alt="${product.name}">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">Narx: ${product.price}</p>
                        <a href="${pageContext.request.contextPath}/productInfo?id=${product.id}"
                           class="btn btn-primary mt-auto">More..</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="/js/js.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

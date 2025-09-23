<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Navbar Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <jsp:include page="/css/css.jsp"/>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-primary navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Bosh sahifa</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarContent" aria-controls="navbarContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/paymentAndDelivery">Tolov va yetkazib berish</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/aboutUs">Biz Haqimizda</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/news">Yangiliklar</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/contacts">Bizning Kontaktlar</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/management/add-product">Add Product</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/management/add-brand">Add Brand</a></li>
            </ul>

            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Mahsulotlarni qidirish..." aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Qidirish</button>
            </form>
        </div>
    </div>
</nav>

<jsp:include page="/js/js.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

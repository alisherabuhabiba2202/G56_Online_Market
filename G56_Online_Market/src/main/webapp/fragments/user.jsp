<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.09.2025
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cabinet</title>
    <jsp:include page="../css/css.jsp"/>
</head>
<body>
<%--<jsp:include page="NavbarHomePage.jsp"/>--%>
<nav class="navbar bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Bosh sahifa</a>
        <a class="navbar-brand" href="/products">Mahsulotlar</a>
        <a class="navbar-brand" href="/brands">Brandlar</a>
        <a class="navbar-brand" href="/paymentAndDelivery">Tolov vayetkazib berish</a>
        <a class="navbar-brand" href="/aboutUs">Biz Haqimizda</a>
        <a class="navbar-brand" href="/news">Yangiliklar</a>
        <a class="navbar-brand" href="/contacts">Bizning Kontaktlar</a>
        <a class="navbar-brand" href="/cabinet">Kabinet</a>
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Mahsulotlarni qidirish..." aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Qidirish</button>
        </form>
    </div>
</nav>
<jsp:include page="../js/js.jsp"/>
</body>
</html>

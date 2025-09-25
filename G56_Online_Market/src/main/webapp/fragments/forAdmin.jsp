<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.09.2025
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../css/css.jsp"/>
</head>
<body>
<nav class="navbar bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Bosh sahifa</a>
        <a class="navbar-brand" href="/products">Mahsulotlar</a>
        <a class="navbar-brand" href="/brands">Brandlar</a>
        <a class="navbar-brand" href="/changeUserRole">User rolini o'zgartirish</a>
        <a class="navbar-brand" href="/changeUserActive">User faolligini o'zgartirish</a>
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Mahsulotlarni qidirish..." aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Qidirish</button>
        </form>
    </div>
</nav>

<jsp:include page="../js/js.jsp"/>
</body>
</html>

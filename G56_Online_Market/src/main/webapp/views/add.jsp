<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 22.09.2025
  Time: 09:45
  Add Page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Add Product</title>
    <jsp:include page="../css/css.jsp"/>
</head>
<body>
<jsp:include page="../fragments/NavbarHomePage.jsp"/>

<div class="container mt-5">
    <h2 class="mb-4">Yangilik qo‘shish</h2>
    <form action="/newsAdd" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label for="name" class="form-label">Yangilik nomi</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Yangilik tavsifi</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>

        <div class="mb-3">
            <label for="photo" class="form-label">Rasm yuklash</label>
            <input type="file" class="form-control" id="photo" name="photo">
        </div>

        <button type="submit" class="btn btn-success">Qo‘shish</button>
        <a href="/views/news.jsp" class="btn btn-secondary">Bekor qilish</a>
    </form>
</div>

</body>
</html>

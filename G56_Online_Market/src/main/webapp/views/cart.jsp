<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Savat</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2 class="mb-4">🛒 Sizning savatingiz</h2>

<c:if test="${empty cartItems}">
    <div class="alert alert-info">
        Savatingiz bo‘sh. <a href="${pageContext.request.contextPath}/home">Mahsulotlarni ko‘rish</a>
    </div>
</c:if>

<c:if test="${not empty cartItems}">
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>Mahsulot</th>
            <th>Narx</th>
            <th>Soni</th>
            <th>Jami</th>
            <th>Amallar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cartItems}">
            <tr>
                <td>${item.products.name}</td>
                <td>${item.products.price} so‘m</td>
                <td>${item.quantity}</td>
                <td>${item.products.price * item.quantity} so‘m</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/cart" class="d-inline">
                        <input type="hidden" name="productId" value="${item.products.id}">
                        <button type="submit" name="action" value="add" class="btn btn-sm btn-success">+</button>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/cart" class="d-inline">
                        <input type="hidden" name="productId" value="${item.products.id}">
                        <button type="submit" name="action" value="decrease" class="btn btn-sm btn-warning">-</button>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/cart" class="d-inline">
                        <input type="hidden" name="productId" value="${item.products.id}">
                        <button type="submit" name="action" value="remove" class="btn btn-sm btn-danger">❌</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h4 class="mt-3">Umumiy narx: <span class="text-success">${totalPrice} so‘m</span></h4>

    <form method="post" action="${pageContext.request.contextPath}/cart" class="mt-3">
        <button type="submit" name="action" value="clear" class="btn btn-outline-danger">🗑 Savatni tozalash</button>
        <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary">✅ Buyurtma berish</a>
    </form>
</c:if>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<h2>Are you sure you want to delete this product?</h2>

<form action="${pageContext.request.contextPath}/productDelete" method="post">
    <input type="hidden" name="id" value="${id}"/>
    <button type="submit">Yes, Delete</button>
</form>

<form action="${pageContext.request.contextPath}/products" method="get">
    <button type="submit">No, Cancel</button>
</form>
</body>
</html>

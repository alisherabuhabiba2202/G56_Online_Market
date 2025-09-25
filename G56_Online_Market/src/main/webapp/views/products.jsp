<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h2>Products List</h2>

<a href="${pageContext.request.contextPath}/productAdd">Add New Product</a>
<br/><br/>

<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Brand</th>
        <th>Image</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.brand.name}</td>
            <td>
                <c:if test="${not empty product.filePath}">
                    <img src="file:///${product.filePath}" width="100" height="100"/>
                </c:if>
            </td>
            <td>
                <!-- Delete -->
                <form action="${pageContext.request.contextPath}/productDelete" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${product.id}"/>
                    <button type="submit">Delete</button>
                </form>

                <!-- Update -->
                <form action="${pageContext.request.contextPath}/productUpdate" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${product.id}"/>
                    <button type="submit">Update</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

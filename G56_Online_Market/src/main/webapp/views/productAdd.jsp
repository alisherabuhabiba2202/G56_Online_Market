<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h2>Add Product</h2>
<form action="${pageContext.request.contextPath}/productAdd" method="post" enctype="multipart/form-data">
    Name: <input type="text" name="name" required/><br/>
    Description: <input type="text" name="description"/><br/>
    Price: <input type="number" step="0.01" name="price" required/><br/>

    File: <input type="file" name="file" required/><br/>

    Brand:
    <select name="brandId">
        <c:forEach var="brand" items="${brands}">
            <option value="${brand.id}">${brand.name}</option>
        </c:forEach>
    </select><br/>

    <button type="submit">Add Product</button>
</form>
</body>
</html>

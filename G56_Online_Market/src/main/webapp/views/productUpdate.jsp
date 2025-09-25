<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<h2>Update Product</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/productUpdate" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${product.id}"/>

    Name: <input type="text" name="name" value="${product.name}" required/><br/>
    Description: <input type="text" name="description" value="${product.description}"/><br/>
    Price: <input type="number" step="0.01" name="price" value="${product.price}" required/><br/>

    Upload New File: <input type="file" name="file" required/><br/><br/>

    Brand:
    <select name="brandId">
        <c:forEach var="brand" items="${brands}">
            <option value="${brand.id}" <c:if test="${brand.id == product.brand.id}">selected</c:if>>
                    ${brand.name}
            </option>
        </c:forEach>
    </select><br/><br/>

    <button type="submit">Update</button>
</form>

</body>
</html>

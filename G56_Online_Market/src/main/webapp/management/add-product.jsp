<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            margin-top: 50px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 {
            font-weight: bold;
            color: #343a40;
        }
        .btn-primary:hover {
            background-color: #0069d9;
        }
    </style>
    <jsp:include page="/css/css.jsp"/>
    <jsp:include page="/fragments/NavbarHomePage.jsp"/>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">

                <h2 class="text-center mb-4">Add Product</h2>

                <form action="${pageContext.request.contextPath}/management/add-product"
                      method="post" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label for="productName" class="form-label">Product Name</label>
                        <input type="text" class="form-control" id="productName" name="name"
                               placeholder="Enter product name..." required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description"
                                  placeholder="Enter product description..."></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" step="10000" class="form-control" id="price" name="price"
                               placeholder="Enter price..." required>
                    </div>

                    <div class="mb-3">
                        <label for="brand" class="form-label">Brand</label>
                        <select class="form-select" id="brand" name="brand_id" required>
                            <c:forEach var="brand" items="${brands}">
                                <option value="${brand.id}">${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="image" class="form-label">Product Image</label>
                        <input type="file" class="form-control" id="image" name="image" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Submit</button>

                </form>
                <br>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary btn-back">Back to Home</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/js/js.jsp"/>
</body>
</html>

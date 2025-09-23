<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Brand</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <jsp:include page="/css/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/NavbarHomePage.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4 mt-5 shadow-sm">
                <h2 class="text-center mb-4">Add Brand</h2>

                <form action="${pageContext.request.contextPath}/management/add-brand" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="brandName" class="form-label">Brand Name</label>
                        <input type="text" class="form-control" id="brandName" name="name" placeholder="Enter brand name..." required>
                    </div>

                    <div class="mb-3">
                        <label for="brandImage" class="form-label">Brand Image</label>
                        <input type="file" class="form-control" id="brandImage" name="image" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Submit</button>
                </form>

                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-100 mt-3">Back to Home</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/js/js.jsp"/>
</body>
</html>

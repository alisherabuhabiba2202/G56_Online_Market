<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uz.pdp.g56_online_market.entities.Products" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <jsp:include page="/css/css.jsp"/>
    <jsp:include page="/fragments/NavbarHomePage.jsp"/>
</head>
<body>
<div class="container mt-5">
    <%
        Products product = (Products) request.getAttribute("product");
        String error = (String) request.getAttribute("error");
        if(error != null) {
    %>
    <div class="alert alert-danger"><%= error %></div>
    <%
    } else if(product != null) {
    %>
    <div class="row">
        <div class="col-md-6">
            <img src="<%= request.getContextPath() %>/files/images/product_img/<%= product.getFilePath() %>"
                 class="img-fluid rounded" alt="<%= product.getName() %>">
        </div>
        <div class="col-md-6">
            <h2><%= product.getName() %></h2>
            <h4>Price: <%= product.getPrice() %></h4>
            <p><%= product.getDescription() %></p>
            <a href="<%= request.getContextPath() %>/home" class="btn btn-secondary mt-3">Back to Home</a>
        </div>
    </div>
    <% } %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/js/js.jsp"/>
</body>
</html>

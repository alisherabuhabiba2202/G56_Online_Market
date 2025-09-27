<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.09.2025
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="java.util.*" %>
<%@ page import="uz.pdp.g56_online_market.entities.News" %>
<%@ page import="uz.pdp.g56_online_market.daos.NewsDAO" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../css/css.jsp"/>
</head>
<body>
<jsp:include page="../fragments/NavbarHomePage.jsp"/>
<div class="container">
    <br>
    <div class="row mb-3">
        <div class="col-md-6 d-flex align-items-center">
            <h1 class="m-0">Yangiliklar</h1>
        </div>
        <div class="col-md-6 d-flex justify-content-end align-items-center">
            <a href="/views/add.jsp" class="btn btn-primary">NewsAdd</a>
        </div>
    </div>

    <div class="row justify-content-center">
        <%
            List<News> news = NewsDAO.getAllNews();
            for (News n : news) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card h-100" style="width: 18rem;">
                <img src="/image?id=<%=n.getId()%>"
                     class="card-img-top mx-auto d-block"
                     width="250" height="200" alt="new.png">
                <div class="card-body text-center">
                    <h5 class="card-title"><%= n.getTitle() %></h5>
                    <p class="card-text"><%= n.getDescription() %></p>

                    <div class="d-flex gap-2 justify-content-center">
                        <a href="/views/edit.jsp?id=<%=n.getId()%>" class="btn btn-primary">EDIT</a>
                        <form action="/newsDelete?id=<%= n.getId() %>" method="post" class="m-0 p-0">
                            <button type="submit" class="btn btn-danger">DELETE</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%
            }
        %>
    </div>
</div>

<jsp:include page="../js/js.jsp"/>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.09.2025
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../css/css.jsp"/>
</head>
<body>
<jsp:include page="../fragments/NavbarHomePage.jsp"/>

<div class="row justify-content-center text-center">
    <h1 class="mb-4">Sayitimizga xush kelibsiz</h1>
    <div class="col-md-4 d-flex justify-content-center">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">100+ Model</h5>
                <p class="card-text">Eng so'ngi modellardagi noutbooklar</p>
                <a href="/" class="card-link">Maxsulotlarni ko'rish</a>
            </div>
        </div>
    </div>
    <div class="col-md-4 d-flex justify-content-center">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Tekin dostavka</h5>
                <p class="card-text">Toshkent shahar bo'ylab tekin viloyatlarga kelishilgan holda</p>
            </div>
        </div>
    </div>
    <div class="col-md-4 d-flex justify-content-center">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Har kuni 10:00 - 22:00 gacha</h5>
                <p class="card-text">Dam olishsiz har kuni xizmatingizdamiz</p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../js/js.jsp"/>
</body>
</html>

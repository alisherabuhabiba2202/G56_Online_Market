<%@ page import="uz.pdp.g56_online_market.daos.NewsDAO" %>
<%@ page import="uz.pdp.g56_online_market.entities.News" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 23.09.2025
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <jp:include page="../css/css.jsp"/>
</head>
<body>
<jsp:include page="../fragments/NavbarHomePage.jsp"/>
<% int newsId = Integer.parseInt(request.getParameter("id"));
    System.out.println(newsId);
    News news = NewsDAO.getNewsById(newsId);
%>
<div class="container mt-5">
    <h2 class="mb-4">Mahsulotni edit qilish</h2>
    <form action="/edit" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label for="name" class="form-label">Mahsulot nomi</label>
            <input value="<%=news.getTitle()%>" type="text" class="form-control" id="name" name="name" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Tavsif</label>
            <textarea  class="form-control" id="description" name="description" rows="3"><%=news.getDescription()%></textarea>
        </div>

        <div class="mb-3">
            <label for="photo" class="form-label">Rasm yuklash</label>
            <div class="image-preview">
                <img id="previewImage"
                     src="/image?id=<%=news.getId()%>"
                     alt="Rasm"
                     class="img-thumbnail"
                     style="max-width: 200px; cursor: pointer;">

                <input type="file" class="form-control d-none" id="photo" name="photo" accept="image/*">
            </div>
        </div>

        <input type="hidden" name="id" value="<%=news.getId()%>">

        <button type="submit" class="btn btn-success">Qo‘shish</button>
        <a href="/views/news.jsp" class="btn btn-secondary">Bekor qilish</a>
    </form>
</div>
</body>
</html>
<script>
    const previewImage = document.getElementById('previewImage');
    const fileInput = document.getElementById('photo');

    previewImage.addEventListener('click', () => {
        fileInput.click();
    });

    fileInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (file) {
            previewImage.src = URL.createObjectURL(file);
        }
    });
</script>

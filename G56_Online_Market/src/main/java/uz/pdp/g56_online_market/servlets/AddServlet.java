package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.g56_online_market.daos.NewsDAO;
import uz.pdp.g56_online_market.entities.News;

import java.io.IOException;

@WebServlet("/add")
@MultipartConfig
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("name");
        String description = req.getParameter("description");
        Part part = req.getPart("photo");
        byte[] bytes = part.getInputStream().readAllBytes();

        System.out.println(title+description);
        NewsDAO.saveNews(new News( title, description,bytes));
        resp.sendRedirect("/views/news.jsp");
    }
}

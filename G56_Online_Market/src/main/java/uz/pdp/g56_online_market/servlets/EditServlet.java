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

@WebServlet("/edit")
@MultipartConfig
public class EditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("name");
        String description = req.getParameter("description");

        Part photoPart = req.getPart("photo");
        byte[] photo = photoPart.getInputStream().readAllBytes();
        NewsDAO.updateNews(new News(title, description, photo),id);
        resp.sendRedirect("/views/news.jsp");
    }

}

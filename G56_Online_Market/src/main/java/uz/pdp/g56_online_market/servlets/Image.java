package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.daos.NewsDAO;

import java.io.IOException;

@WebServlet("/image")
public class Image extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        byte[] photo = NewsDAO.getByteaById(id);

        if (photo != null) {
            resp.setContentType("image/jpeg");
            resp.setContentLength(photo.length);
            resp.getOutputStream().write(photo);
            resp.getOutputStream().flush();
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}


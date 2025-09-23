package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.dtos.NewsDTO;
import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.services.NewsServices;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;
import java.util.List;


@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    private NewsServices newsServices = new NewsServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsDTO> newsByPageable = newsServices.getNewsByPageable(1,10);
        req.setAttribute("news", newsByPageable);
        req.getRequestDispatcher("/views/news.jsp").forward(req, resp);
    }
}

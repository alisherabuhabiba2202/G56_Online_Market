package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;
import java.util.List;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> productsByPageable = productService.getProductsByPageable(1, 10);
        req.setAttribute("products", productsByPageable);
        req.getRequestDispatcher("/views/homePage.jsp").forward(req, resp);
    }
}

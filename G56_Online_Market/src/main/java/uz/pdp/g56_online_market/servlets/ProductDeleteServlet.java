package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;

@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idStr = req.getParameter("id");

        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        int id = Integer.parseInt(idStr);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/views/productDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}

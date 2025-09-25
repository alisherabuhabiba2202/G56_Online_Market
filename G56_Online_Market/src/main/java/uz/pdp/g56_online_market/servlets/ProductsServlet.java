package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.entities.Products;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Products> products = productService.getAllProducts();
        req.setAttribute("products", products); // ← muhim, products.jsp ga jo'natiladi
        req.getRequestDispatcher("/views/products.jsp").forward(req, resp);
    }
}

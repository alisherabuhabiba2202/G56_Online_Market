package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.daos.ProductDAO;
import uz.pdp.g56_online_market.entities.Products;

import java.io.IOException;

@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if(idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Products product = productDAO.getProductById(id);
                if(product != null) {
                    request.setAttribute("product", product);
                } else {
                    request.setAttribute("error", "Product not found!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid product ID!");
            }
        } else {
            request.setAttribute("error", "Product ID is missing!");
        }

        request.getRequestDispatcher("/views/productInfo.jsp").forward(request, response);
    }
}

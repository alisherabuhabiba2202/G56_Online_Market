package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.dtos.BrandDTO;
import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.services.BrandService;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;
import java.util.List;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final BrandService brandService = new BrandService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getRequestURI();

        List<ProductDTO> productsByPageable = productService.getProductsByPageable(1, 10);
        List<BrandDTO> brandsByPageable = brandService.getBrandsByPageable(1, 10);

        req.setAttribute("products", productsByPageable);
        req.setAttribute("brands", brandsByPageable);

        req.getRequestDispatcher("/homePage.jsp").forward(req, resp);
    }
}

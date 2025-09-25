package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.g56_online_market.entities.Brands;
import uz.pdp.g56_online_market.services.BrandService;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebServlet("/productAdd")
@MultipartConfig
public class ProductAddServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private BrandService brandService = new BrandService();

    static Path uploadPath = Path.of(System.getProperty("user.home"), "Desktop", "G56Products");

    @Override
    public void init() throws ServletException {
        if (Files.notExists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // brendlarni JSPga yuboramiz
        req.setAttribute("brands", brandService.getAllBrands());
        req.getRequestDispatcher("/views/productAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));

        // File upload
        Part filePart = req.getPart("file");
        String originalFileName = filePart.getSubmittedFileName();
        String fileExt = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String newFileName = System.currentTimeMillis() + fileExt; // unique nom
        try (InputStream is = filePart.getInputStream()) {
            Files.copy(is, uploadPath.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
        }
        String filePath = uploadPath.resolve(newFileName).toString();

        // Brand olish
        Integer brandId = Integer.parseInt(req.getParameter("brandId"));
        Brands brand = brandService.getBrandById(brandId);

        // Productni saqlash
        productService.addProduct(name, description, price, filePath, brand);

        resp.sendRedirect(req.getContextPath() + "/products"); // ro‘yxatga qaytish
    }
}

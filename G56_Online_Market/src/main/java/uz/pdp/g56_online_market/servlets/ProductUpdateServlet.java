package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.g56_online_market.entities.Brands;
import uz.pdp.g56_online_market.entities.Products;
import uz.pdp.g56_online_market.services.BrandService;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/productUpdate")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 5 * 1024 * 1024,   // 5MB
        maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class ProductUpdateServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final BrandService brandService = new BrandService();
    private final String uploadDir = "uploads"; // projektdagi uploads papka

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        int id = Integer.parseInt(idParam);
        Products product = productService.getById(id);

        if (product == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        req.setAttribute("product", product);
        req.setAttribute("brands", brandService.getAllBrands());

        req.getRequestDispatcher("/views/productUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        int brandId = Integer.parseInt(req.getParameter("brandId"));
        Brands brand = brandService.getBrandById(brandId);

        // Faylni olish (yangi fayl majburiy)
        Part filePart = req.getPart("file");
        if (filePart == null || filePart.getSize() == 0) {
            req.setAttribute("error", "You must select a file!");
            doGet(req, resp);
            return;
        }

        String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName();
        String realPath = req.getServletContext().getRealPath("/") + uploadDir;
        File uploads = new File(realPath);
        if (!uploads.exists()) uploads.mkdirs();
        filePart.write(new File(uploads, fileName).getAbsolutePath());

        // Productni yangilash
        Products product = productService.getById(id);
        if (product != null) {
            product.setName(name);
            product.setDescription(desc);
            product.setPrice(price);
            product.setBrand(brand);
            product.setFilePath(uploadDir + "/" + fileName);

            productService.updateProduct(product.getId(), product.getName(), product.getDescription(),
                    product.getPrice(), product.getFilePath(), product.getBrand());
        }

        resp.sendRedirect(req.getContextPath() + "/products");
    }
}

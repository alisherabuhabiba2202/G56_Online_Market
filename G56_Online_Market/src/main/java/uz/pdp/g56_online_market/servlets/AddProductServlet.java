package uz.pdp.g56_online_market.servlets;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.daos.BrandDAO;
import uz.pdp.g56_online_market.entities.Brands;
import uz.pdp.g56_online_market.entities.Products;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/management/add-product")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8"); // UTF-8 support

            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String priceStr = req.getParameter("price");
            Integer brandId = Integer.parseInt(req.getParameter("brand_id"));

            if(name == null || name.isBlank() || priceStr == null || priceStr.isBlank()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form ma'lumotlari to‘liq emas");
                return;
            }

            Double price = Double.parseDouble(priceStr);

            Part filePart = req.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            String uploadDir = getServletContext().getRealPath("/files/product_img");
            File dir = new File(uploadDir);
            if(!dir.exists() && !dir.mkdirs()) throw new IOException("Folderni yaratib bo‘lmadi");

            filePart.write(uploadDir + File.separator + fileName);

            EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Brands brands = em.find(Brands.class, brandId);
            Products products = Products.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .filePath("files/product_img/" + fileName)
                    .brand(brands)
                    .build();
            em.persist(products);
            em.getTransaction().commit();
            em.close();

            resp.sendRedirect(req.getContextPath() + "/");
        } catch(Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Xatolik yuz berdi");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BrandDAO brandDAO = new BrandDAO();
        List<Brands> brands = brandDAO.getBrandsByPageable(1,100);
        req.setAttribute("brands", brands);
        System.out.println("Brands count = " + brands.size());
        req.getRequestDispatcher("/management/add-product.jsp").forward(req, resp);

    }
}

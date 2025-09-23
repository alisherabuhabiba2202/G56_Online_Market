    package uz.pdp.g56_online_market.servlets;

    import jakarta.persistence.EntityManager;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.MultipartConfig;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.*;
    import uz.pdp.g56_online_market.config.JpaConfig;
    import uz.pdp.g56_online_market.entities.Brands;
    import java.io.File;
    import java.io.IOException;
    import java.io.InputStream;
    import java.nio.file.*;
    import java.util.List;

    @WebServlet("/management/add-brand")
    @MultipartConfig
    public class AddBrandServlet extends HttpServlet {

        static Path path = Path.of("C:\\Users\\HP VICTUS\\IdeaProjects\\G56_Online_Market\\G56_Online_Market\\src\\main\\webapp\\files\\images\\brand_img");

        @Override
        public void init(){
            if (Files.notExists(path)){
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/management/add-brand.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                req.setCharacterEncoding("UTF-8");

                String name = req.getParameter("name");
                if(name == null || name.isBlank()){
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brand nomi bo‘sh bo‘lishi mumkin emas");
                    return;
                }
                Part file = req.getPart("image");
                String fileName = file.getSubmittedFileName();
                String fileExtention = fileName.substring(fileName.lastIndexOf('.'));
                System.out.println(fileName);
                InputStream inputStream = file.getInputStream();
//                int available = inputStream.available();
                if (!Files.exists(path)) {
                    Files.createDirectories(path);  // creates all non-existing parent directories
                }
                Files.copy(inputStream,path.resolve(name + fileExtention), StandardCopyOption.REPLACE_EXISTING);
                resp.getWriter().write(fileName + " Successfully uploaded!");

                EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
                em.getTransaction().begin();

                Brands brand = new Brands();
                brand.setName(name);
                brand.setFilepath("/files/brand_img/" + fileName);

                em.persist(brand);
                em.getTransaction().commit();
                em.close();

                resp.sendRedirect(req.getContextPath() + "/management/add-brand.jsp?success=true");

            } catch(Exception e){
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Xatolik yuz berdi: " + e.getMessage());
            }

        }
    }

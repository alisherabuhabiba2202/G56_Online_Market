package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.g56_online_market.daos.UserDAO;
import uz.pdp.g56_online_market.entities.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet("/cabinet/update-picture")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class UpdatePictureServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect("/login.jsp");
            return;
        }


        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();


        if (fileName == null || !(fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".png"))) {
            resp.setStatus(400);
            resp.getWriter().write("Faqat JPG yoki PNG fayllar ruxsat etiladi!");
            return;
        }


        String uploadDir = getServletContext().getRealPath("/uploads");
        Files.createDirectories(Paths.get(uploadDir));
        String uniqueFileName = UUID.randomUUID() + "_" + fileName;
        String filePath = uploadDir + File.separator + uniqueFileName;


        filePart.write(filePath);


        User user = userDAO.findById(userId);
        if (user != null) {
            user.setProfilePicture("/uploads/" + uniqueFileName);
            userDAO.update(user);
            req.getSession().setAttribute("profilePicture", user.getProfilePicture());
            resp.getWriter().write("Rasim muvaffaqiyatli yuklandi!");
        } else {
            resp.setStatus(400);
            resp.getWriter().write("Foydalanuvchi topilmadi!");
        }
    }
}

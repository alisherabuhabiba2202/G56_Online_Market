package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import uz.pdp.g56_online_market.daos.UserDAO;
import uz.pdp.g56_online_market.entities.User;
import uz.pdp.g56_online_market.services.PasswordService;

import java.io.IOException;

@WebServlet("/cabinet/update-password")
public class UpdatePasswordServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        if (userId == null) {
            resp.sendRedirect("/login.jsp");
            return;
        }

        User user = userDAO.findById(userId);
        if (user != null && PasswordService.verifyPassword(oldPassword, user.getPassword())) {
            user.setPassword(PasswordService.hashPassword(newPassword));
            userDAO.update(user);
            resp.getWriter().write("Parol o'zgartirildi!");
        } else {
            resp.setStatus(400);
            resp.getWriter().write("Xato: Eski parol noto'g'ri!");
        }
    }
}

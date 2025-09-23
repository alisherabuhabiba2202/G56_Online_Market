package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.entities.Users;
import uz.pdp.g56_online_market.services.UsersService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UsersService usersService =  new UsersService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users user = new Users().builder().username(username).password(password).build();
        boolean login = usersService.login(user);
        if(login == true){
            req.setAttribute("message","success");
        }else {
            req.setAttribute("error","fail");
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req,resp);
    }
}

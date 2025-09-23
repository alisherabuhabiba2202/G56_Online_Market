package uz.pdp.g56_online_market.services;

import jakarta.servlet.http.HttpSession;
import uz.pdp.g56_online_market.daos.UsersDAO;
import uz.pdp.g56_online_market.entities.Users;
import uz.pdp.g56_online_market.enums.RoleName;
import uz.pdp.g56_online_market.utils.PasswordEncoder;

import java.util.List;

public class UsersService {
   private final UsersDAO usersDAO = new UsersDAO();

   public  boolean saveUser(Users user,RoleName role){
        return usersDAO.saveUser(user,role);
   }

   public boolean login(String username, String password, HttpSession session) {
       Users userByUsername = usersDAO.findUserByUsername(username);
       if (!PasswordEncoder.matchPassword(password, userByUsername.getPassword())) {
           throw  new RuntimeException("Invalid username or password");
       }
       session.setAttribute("username",username);
       session.setAttribute("role",userByUsername.getRoleName().name());
       return true;
   }



    public boolean deleteUser(Integer id) {
        return usersDAO.deleteUser(id);
    }

    public boolean changeActivateUser(Integer id,boolean status) {
        return usersDAO.changeActivateUser(id,status);
    }


    public Users findUserByUsername(String username) {
        return usersDAO.findUserByUsername(username);
    }

    public List<Users> findUsersByRoleName(RoleName roleName)
    {
        return usersDAO.findUsersByRoleName(roleName);
    }

    public boolean changeRoleName(Integer id, RoleName roleName) {
       return usersDAO.changeRoleName(id,roleName);
    }
}

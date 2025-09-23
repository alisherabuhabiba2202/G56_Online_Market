package uz.pdp.g56_online_market.services;

import uz.pdp.g56_online_market.daos.UsersDAO;
import uz.pdp.g56_online_market.entities.Users;
import uz.pdp.g56_online_market.enums.RoleName;

import java.util.List;

public class UsersService {
   private final UsersDAO usersDAO = new UsersDAO();

   public  boolean saveUser(Users user){
        return UsersDAO.saveUser(user);
   }

   public boolean login(Users user) {
       if (user == null || user.getUsername() == null || user.getPassword() == null) {
           return false;
       }
       Users userByUsername = UsersDAO.findUserByUsername(user.getUsername());
       if (userByUsername == null || userByUsername.getPassword() == null || !userByUsername.isActive()) {
           return false;
       }
       return userByUsername.getPassword().equals(user.getPassword());
   }

   public boolean saveAccountant(Users user){
       return usersDAO.saveAccountant(user);
   }

    public boolean deleteUser(Integer id) {
        return usersDAO.deleteUser(id);
    }

    public boolean activateUser(Integer id) {
        return UsersDAO.activateUser(id);
    }

    public boolean deactivateUser(Integer id) {
        return UsersDAO.deactivateUser(id);
    }

    public Users findUserByUsername(String username) {
        Users userByUsername = UsersDAO.findUserByUsername(username);
        return userByUsername;
    }

    public List<Users> findUsersByRoleName(RoleName roleName) {
        return usersDAO.findUsersByRoleName(roleName);
    }

}

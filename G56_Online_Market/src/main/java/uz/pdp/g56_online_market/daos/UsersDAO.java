package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Users;
import uz.pdp.g56_online_market.enums.RoleName;

import java.util.List;

public class UsersDAO {

    public boolean changeRoleName(Integer userId, RoleName roleName){
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            int rowsAffected = em.createQuery("UPDATE Users u SET u.roleName = :roleName WHERE u.id = :id")
                    .setParameter("roleName", roleName)
                    .setParameter("id", userId)
                    .executeUpdate();
            em.getTransaction().commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deleteUser(Integer id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Users user = em.find(Users.class, id);
            if (user == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }



    public  boolean saveUser(Users user,RoleName roleName) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
                user.setRoleName(roleName);
                user.setActive(true);
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
                return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Users findUserByUsername(String username) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        Users user = null;
        try {
            em.getTransaction().begin();
            user = em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
                    .setParameter("username", username)
                    .getSingleResult();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }

    public List<Users> findUsersByRoleName(RoleName roleName) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            List<Users> users;
            if (roleName == null) {
                users = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
            } else {
                users = em.createQuery("SELECT u FROM Users u WHERE u.roleName = :roleName", Users.class)
                        .setParameter("roleName", roleName)
                        .getResultList();
            }
            em.getTransaction().commit();
            return users;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public  boolean changeActivateUser(Integer id,boolean active) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            int rowsAffected = em.createQuery("UPDATE Users u SET u.active = :active WHERE u.id = :id")
                    .setParameter("active", active)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("uz.pdp.g56_online_market");
    public User findById(long id) {
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(User.class, id);
        }finally{
            em.close();
        }
    }
    public void update(User user) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }


}
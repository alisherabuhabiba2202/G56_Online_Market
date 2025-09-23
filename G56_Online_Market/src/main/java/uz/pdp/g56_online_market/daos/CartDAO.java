package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Cart;
import uz.pdp.g56_online_market.entities.User;


public class CartDAO {

    public Cart findByUser(User user) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {


            TypedQuery<Cart> query = entityManager.createQuery(
                    "select c from Cart c where c.user = :user"
                    , Cart.class);

            return query.setParameter("user", user)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            entityManager.close();
        }
    }

    public void save(Cart cart) {
        EntityManager cartEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = cartEntityManager.getTransaction();

        try {
            transaction.begin();
            cartEntityManager.persist(cart);
            transaction.commit();

        } catch (RuntimeException e) {

            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            cartEntityManager.close();
        }
    }

    public void update(Cart cart) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cart);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }


    }
}

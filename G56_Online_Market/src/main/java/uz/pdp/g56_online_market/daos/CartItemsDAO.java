package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Cart;
import uz.pdp.g56_online_market.entities.CartItems;
import uz.pdp.g56_online_market.entities.Products;

import java.util.List;

public class CartItemsDAO {


    public CartItems findByCartAndProduct(Cart cart, Products product) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<CartItems> query = em.createQuery(
                    "select ci from CartItems ci where ci.cart = :cart and ci.products = :product",
                    CartItems.class
            );
            return query.setParameter("cart", cart)
                    .setParameter("product", product)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    public List<CartItems> findByCart(Cart cart) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<CartItems> query = em.createQuery(
                    "select ci from CartItems ci where ci.cart = :cart", CartItems.class
            );
            return query.setParameter("cart", cart).getResultList();
        } finally {
            em.close();
        }
    }

    public void save(CartItems cartItem) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cartItem);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(CartItems cartItem) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cartItem);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(CartItems cartItem) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            CartItems managed = em.merge(cartItem);
            em.remove(managed);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

}



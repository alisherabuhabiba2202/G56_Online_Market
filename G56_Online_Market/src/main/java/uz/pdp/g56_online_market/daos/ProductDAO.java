package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Products;

import java.util.List;

public class ProductDAO {

    public List<Products> getProductsByPageable(int page, int size) {
        EntityManager productEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            productEntityManager.getTransaction().begin();
            Query query = productEntityManager.createNativeQuery("select * from products limit " + size + " offset " + ((page - 1) * size));
            List<Products> products = query.getResultList();
            productEntityManager.getTransaction().commit();
            return products;
        } catch (Exception ex) {
            productEntityManager.getTransaction().rollback();
            throw ex;
        }
        finally {
            productEntityManager.close();
        }
    }

    public int getProductQuatityById(int id) {
        EntityManager productEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            productEntityManager.getTransaction().begin();
            Query query = productEntityManager.createNativeQuery("select  sum(i.quntity) - sum(o.quntity) from income i join outcome o on i.product_id = o.product_id where i.product_id = " + id);
            Integer productQuatity = (Integer) query.getSingleResult();
            productEntityManager.getTransaction().commit();
            return productQuatity;
        } catch (Exception ex) {
            productEntityManager.getTransaction().rollback();
            throw ex;
        }
        finally {
            productEntityManager.close();
        }
    }
}

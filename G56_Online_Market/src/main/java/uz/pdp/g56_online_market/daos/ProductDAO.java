package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Products> getProductsByPageable(int page, int size) {
        EntityManager productEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            productEntityManager.getTransaction().begin();
            Query query = productEntityManager.createNativeQuery("select * from products limit " + size + " offset " + ((page - 1) * size));
            List<Object[]> resultList = query.getResultList();
            List<Products> productsList = new ArrayList<>();
            for (Object[] obj : resultList) {
                Products product = new Products().builder()
                        .id((Integer) obj[0])
                        .name((String) obj[1])
                        .description((String) obj[2])
                        .price((Double) obj[3])
                        .filePath((String) obj[4])
                        .build();
                productsList.add(product);
            }
            productEntityManager.getTransaction().commit();
            return productsList;
        } catch (Exception ex) {
            productEntityManager.getTransaction().rollback();
            throw ex;
        }
        finally {
            productEntityManager.close();
        }
    }

    public int getProductQuantityById(int id) {
        EntityManager productEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            productEntityManager.getTransaction().begin();
            Query query = productEntityManager.createNativeQuery("select  sum(i.quantity) - sum(o.quantity) from income i join outcome o on i.product_id = o.product_id where i.product_id = " + id);
            Object singleResult = query.getSingleResult();
            productEntityManager.getTransaction().commit();
            return Integer.parseInt(singleResult.toString());
        } catch (Exception ex) {
                productEntityManager.getTransaction().rollback();
            throw ex;
        }
        finally {
            productEntityManager.close();
        }
    }

    public Products getProductById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Products product = em.find(Products.class, id);
            em.getTransaction().commit();
            return product;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}

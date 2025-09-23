package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {


    public Products findById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Products.class, id);
        } finally {
            em.close();
        }
    }


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

    public int getProductQuatityById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Query query = em.createNativeQuery(
                    "select (select coalesce(sum(quntity),0) from income where product_id=?) " +
                            "- (select coalesce(sum(quntity),0) from outcome where product_id=?)"
            );
            query.setParameter(1, id);
            query.setParameter(2, id);

            Object singleResult = query.getSingleResult();
            tx.commit();

            // Agar natija null bo‘lsa 0 qaytaradi
            return singleResult == null ? 0 : ((Number) singleResult).intValue();

        } catch (Exception ex) {
            if (tx.isActive()) {   // ✅ faqat active bo‘lsa rollback qilamiz
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

}

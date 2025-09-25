package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Products> getProductsByPageable(int page, int size) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            Query query = em.createNativeQuery(
                    "select id, name, description, price, filepath, brand_id " +
                            "from products limit ?1 offset ?2"
            );
            query.setParameter(1, size);
            query.setParameter(2, (page - 1) * size);

            List<Object[]> resultList = query.getResultList();
            List<Products> productsList = new ArrayList<>();

            for (Object[] obj : resultList) {
                Products product = Products.builder()
                        .id(((Number) obj[0]).intValue())   // Integer sifatida
                        .name((String) obj[1])
                        .description((String) obj[2])
                        .price(((Number) obj[3]).doubleValue()) // Double casting xavfsizroq
                        .filePath((String) obj[4])
                        .build();

                Integer brandId = obj[5] != null ? ((Number) obj[5]).intValue() : null;
                if (brandId != null) {
                    product.setBrand(new BrandDAO().getBrandById(brandId));
                }

                productsList.add(product);
            }

            return productsList;
        } finally {
            em.close();
        }
    }


//    public int getProductQuatityById(int id) {
//        EntityManager productEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
//        try {
//            productEntityManager.getTransaction().begin();
//            Query query = productEntityManager.createNativeQuery
//                    ("select  sum(i.quntity) - sum(o.quntity) from income i join outcome o on i.product_id = o.product_id where i.product_id = " + id);
//            Object singleResult = query.getSingleResult();
//            productEntityManager.getTransaction().commit();
//            return Integer.parseInt(singleResult.toString());
//        } catch (Exception e) {
//        if (productEntityManager.getTransaction().isActive()) { // ✅ faqat active bo‘lsa rollback qilamiz
//            productEntityManager.getTransaction().rollback();
//        }
//        throw e;
//    }
//
//        finally {
//            productEntityManager.close();
//        }
//    }

    public Integer getProductQuantityById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            // income va outcome jadvalidan hisoblaymiz
            Query query = em.createNativeQuery(
                    "select coalesce(sum(i.quantity), 0) - coalesce(sum(o.quantity), 0) " +
                            "from income i " +
                            "left join outcome o on i.product_id = o.product_id " +
                            "where i.product_id = :id"  // -- ✅ _id emas, :id bo‘lishi kerak
);
            query.setParameter("id", id);

            Object result = query.getSingleResult();
            return result != null ? Integer.parseInt(result.toString()) : 0;
        } catch (NoResultException e) {
            return 0;
        } finally {
            em.close();
        }
    }


    public void save(Products product) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void update(Products product) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Products product = em.find(Products.class, id);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Products findById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Products.class, id);
        } finally {
            em.close();
        }
    }


    public List<Products> getAllProducts() {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Products p", Products.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}

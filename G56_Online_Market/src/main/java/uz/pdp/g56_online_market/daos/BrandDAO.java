package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Brands;

import java.util.List;

public class BrandDAO {

    public List<Brands> getAllBrands(){
//        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
//        try {
//            entityManager.getTransaction().begin();
//            Query query = entityManager.createNativeQuery("select * from brands", Brands.class);
//            List<Brands> resultList = query.getResultList();
//            return resultList;
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw e;
//        } finally {
//            entityManager.close();
//        }

        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("from Brands", Brands.class).getResultList();
        } finally {
            em.close();
        }

    }

    public Brands getBrandById(int id){
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Brands.class, id);
        } finally {
            em.close();
        }
    }
}

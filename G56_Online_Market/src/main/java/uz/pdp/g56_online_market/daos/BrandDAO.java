package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.Brands;


import java.util.ArrayList;
import java.util.List;

public class BrandDAO {

    public List<Brands> getBrandsByPageable(int page, int size) {
        EntityManager brandEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try{
            brandEntityManager.getTransaction().begin();
            Query query = brandEntityManager.createNativeQuery("select * from brands limit " + size + " offset " + ((page - 1) * size));
            List<Object[]> resultList = query.getResultList();
            List<Brands> brandsList = new ArrayList<>();
            for (Object[] obj : resultList) {
                Brands brands = new Brands().builder()
                        .id((Integer) obj[0])
                        .name((String) obj[1])
                        .filepath((String) obj[2])
                        .build();
                brandsList.add(brands);
            }
            brandEntityManager.getTransaction().commit();
            return brandsList;
        } catch (Exception ex) {
            brandEntityManager.getTransaction().rollback();
            throw ex;
        }
        finally {
            brandEntityManager.close();
        }
    }
    public int getBrandQuantityById(int id) {
        EntityManager brandEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try{
            brandEntityManager.getTransaction().begin();
            Query query = brandEntityManager.createQuery("select count(*) from Brands   where id = " + id);
            Long count = (Long) query.getSingleResult();
            brandEntityManager.getTransaction().commit();
            return Integer.parseInt(count.toString());
    } catch (Exception ex) {
            brandEntityManager.getTransaction().rollback();
            throw ex;
        } finally {
            brandEntityManager.close();
        }
    }

}

package uz.pdp.g56_online_market.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.entities.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDAO {

    public static News getNewsById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            Query query = em.createNativeQuery("select * from news where id = ?1");
            query.setParameter(1, id);
            Object[] row = (Object[]) query.getSingleResult();
            News news = new News();
            news.setId((Integer) row[0]);
            news.setTitle((String) row[1]);
            news.setDescription((String) row[2]);

            tx.commit();
            return news;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    public static byte[] getByteaById(int id) {
        EntityManager em = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            Query query = em.createNativeQuery("select photo from news where id = ?");
            query.setParameter(1, id);

            Object result = query.getSingleResult();
            if (result != null) {
                return (byte[]) result;
            }
            return null;
        } finally {
            em.close();
        }
    }

    public  List<News> getAllNews(int page, int size) {
        EntityManager newsEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            newsEntityManager.getTransaction().begin();
            Query query = newsEntityManager.createNativeQuery("select * from news limit ? offset ?");
            query.setParameter(1, size);
            query.setParameter(2, (page-1) * size);
            List<Object[]> resultList = query.getResultList();
            List<News> newsList2 = new ArrayList<News>();
            for (Object[] obj  : resultList) {
                News news = News.builder()
                        .id((Integer) obj[0])
                        .title((String) obj[1])
                        .description((String) obj[2])
                        .photo((byte[]) obj[3])   // ✅ to‘g‘ri
                        .build();
                newsList2.add(news);
            }
            newsEntityManager.getTransaction().commit();
            return newsList2;

        } catch (Exception e) {
            newsEntityManager.getTransaction().rollback();
            throw e;
        } finally {
            newsEntityManager.close();
        }
    }

    public static void saveNews(News news) {
        try (
                EntityManager newsEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();

        ){
            newsEntityManager.getTransaction().begin();
            Query query = newsEntityManager.createNativeQuery("insert into news(title,description,photo) values(?,?,?)");
            query.setParameter(1, news.getTitle());
            query.setParameter(2, news.getDescription());
            query.setParameter(3, news.getPhoto());
            query.executeUpdate();
            newsEntityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<News> getAllNews() {
        EntityManager newsEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            newsEntityManager.getTransaction().begin();
            Query query = newsEntityManager.createNativeQuery("select * from news");
            List<Object[]> resultList = query.getResultList();
            List<News> newsList2 = new ArrayList<News>();
            for (Object[] obj  : resultList) {
                News news = News.builder()
                        .id((Integer) obj[0])
                        .title((String) obj[1])
                        .description((String) obj[2])
                        .photo((byte[]) obj[3])   // ✅ to‘g‘ri
                        .build();
                newsList2.add(news);
            }
            newsEntityManager.getTransaction().commit();
            return newsList2;
        }catch (Exception e) {
            newsEntityManager.getTransaction().rollback();
            throw e;
        } finally {
            newsEntityManager.close();
        }
    }
    public static void deleteNews(Integer id) {
        try (
                EntityManager newsEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        ){
            newsEntityManager.getTransaction().begin();
            Query query = newsEntityManager.createNativeQuery("delete from news where id = ?");
            query.setParameter(1, id);
            query.executeUpdate();
            newsEntityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateNews(News news , Integer id) {
        try (
                EntityManager newsEntityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
                ){
            newsEntityManager.getTransaction().begin();
            Query query = newsEntityManager.createNativeQuery("update news set title = ?, description = ?, photo = ? where id = ?");
            query.setParameter(1, news.getTitle());
            query.setParameter(2, news.getDescription());
            query.setParameter(3, news.getPhoto());
            query.setParameter(4, id);
            query.executeUpdate();
            newsEntityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

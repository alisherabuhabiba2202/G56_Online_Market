package uz.pdp.g56_online_market.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
            if(entityManagerFactory == null){
                entityManagerFactory = Persistence.createEntityManagerFactory("postgresql_orm");
            }
        return entityManagerFactory;
    }

}

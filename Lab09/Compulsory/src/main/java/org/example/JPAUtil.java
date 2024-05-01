package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emFactory;

    static {
        emFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emFactory;
    }

    public static void close() {
        emFactory.close();
    }
}
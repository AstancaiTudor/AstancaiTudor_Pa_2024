package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();



        em.getTransaction().begin();
        Author author = new Author("Mark Twain");
        em.persist(author);

        Author a = (Author) em.createQuery(
                        "select e from Author e where e.name='Mark Twain'")
                .getSingleResult();
        a.setName("Samuel Langhorne Clemens");
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
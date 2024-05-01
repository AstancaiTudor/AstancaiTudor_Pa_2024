package org.example;


import javax.persistence.EntityManager;
import java.util.List;

public class AuthorRepository {

    public void create(Author author) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    public Author findById(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Author author = em.find(Author.class, id);
        em.close();
        return author;
    }

    public List<Author> findByName(String name) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Author> authors = em.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        em.close();
        return authors;
    }
}


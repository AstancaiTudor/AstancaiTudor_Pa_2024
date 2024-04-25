package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var authors = new AuthorDAO();

            authors.create("Ion Creanga");

            System.out.println(authors.findById(12));
            System.out.println(authors.findByName("William Shakespeare"));


            Database.getConnection().commit();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        } finally {
            Database.closeConnection();
        }
    }
}

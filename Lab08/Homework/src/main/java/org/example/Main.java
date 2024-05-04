package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        AuthorDAO authors = new AuthorDAO();
        BookDAO books = new BookDAO();
        try (Connection con = Database.getConnection()) {
            con.setAutoCommit(false);

            authors.create("A B C D");
            System.out.println(authors.findById(12));
            System.out.println(authors.findByName("William Shakespeare"));

            books.create("The Merchant of Venice", 1, 1);
            System.out.println(books.findByTitle("The Merchant of Venice"));
            System.out.println(books.findById(1));

            con.commit();
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            try {
                if (Database.getConnection() != null) {
                    System.err.println("Transaction is being rolled back");
                    Database.getConnection().rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error during transaction rollback: " + ex.getMessage());
            }
        }
      
    }
}

package org.example;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository("master_directory");
        System.out.println(repository);
    }
}
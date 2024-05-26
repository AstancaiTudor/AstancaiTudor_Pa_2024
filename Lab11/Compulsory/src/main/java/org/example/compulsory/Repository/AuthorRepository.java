package org.example.compulsory.Repository;

import org.example.compulsory.Model.Author;
import org.example.compulsory.Service.AuthorService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

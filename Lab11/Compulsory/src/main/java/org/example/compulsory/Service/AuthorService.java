package org.example.compulsory.Service;

import org.example.compulsory.Model.Author;
import org.example.compulsory.Repository.AuthorRepository;
import org.example.compulsory.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }
}

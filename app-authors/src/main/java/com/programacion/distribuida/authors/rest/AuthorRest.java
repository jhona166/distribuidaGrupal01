package com.programacion.distribuida.authors.rest;

import com.programacion.distribuida.authors.db.Author;
import com.programacion.distribuida.authors.repo.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorRest {

    @Autowired
    private AuthorsRepository authorRepository;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Integer id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/find/{isbn}")
    public List<Author> findByBook(@PathVariable String isbn) {
        return authorRepository.findByBook(isbn).stream()
                .peek(author -> author.setName(author.getName() + " (" + serverPort + ")"))
                .toList();
    }
}
package com.company.Summative2AndriamasinoroMandresy.controllers;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import com.company.Summative2AndriamasinoroMandresy.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository repo;

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return repo.findAll();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable int id) {

        Optional<Author> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAlbum(@RequestBody Author author) {
        return repo.save(author);
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author) {
        repo.save(author);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        repo.deleteById(id);
    }
}

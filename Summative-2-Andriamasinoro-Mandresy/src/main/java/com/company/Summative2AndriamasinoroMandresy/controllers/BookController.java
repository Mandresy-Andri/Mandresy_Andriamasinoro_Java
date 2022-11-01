package com.company.Summative2AndriamasinoroMandresy.controllers;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import com.company.Summative2AndriamasinoroMandresy.models.Book;
import com.company.Summative2AndriamasinoroMandresy.repositories.AuthorRepository;
import com.company.Summative2AndriamasinoroMandresy.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository repo;

    @Autowired
    AuthorRepository authorRepo;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return repo.findAll();
    }

    //Get book by String Author
    @GetMapping("/books/author/{author}")
    public List<Book> getBookById(@PathVariable String author) {
        List<Author> aList =  authorRepo.findByFirstName(author);
        int authorId=0;
        for(Author author1: aList)
            if(author1.getFirstName().toLowerCase().equals(author.toLowerCase()))
                authorId=author1.getId();
        return repo.findByAuthorId(authorId);
    }

    //Get book by Author Id
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {

        Optional<Book> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return repo.save(book);
    }

    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book) {
        repo.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        repo.deleteById(id);
    }
}

package com.company.Summative2AndriamasinoroMandresy.repositories;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import com.company.Summative2AndriamasinoroMandresy.repositories.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();
    }

    @Test
    public void addGetDeleteAuthor() {
        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        repo.save(author);

        //get author by id
        Optional<Author> author1 = repo.findById(author.getId());

        //assert that we get correct author
        assertEquals(author1.get(), author);

        //delete author
        repo.deleteById(author.getId());

        //assert that author was deleted
        author1 = repo.findById(author.getId());
        assertFalse(author1.isPresent());
    }

    @Test
    public void getAllAuthors() {

        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        repo.save(author);

        //add new author
        Author author1 = new Author();
        author1.setFirstName("Cris");
        author1.setLast_name("Tiano");
        author1.setStreet("Real st");
        author1.setCity("Madrida");
        author1.setState("NV");
        author1.setPostal_code("4698");
        author1.setPhone("5423010259");
        author1.setEmail("cr8@gmail.com");
        repo.save(author1);

        //get all authors
        List<Author> authorList = repo.findAll();

        //assert all authors were retrieved
        assertEquals(authorList.size(), 2);

    }

    @Test
    public void updateLabel() {

        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        repo.save(author);

        //update name and city
        author.setFirstName("Chinpin");
        author.setCity("Fredericksboulevard");
        repo.save(author);

        //assert that author was updated
        Optional<Author> author1 = repo.findById(author.getId());
        assertEquals(author1.get(), author);
    }
}
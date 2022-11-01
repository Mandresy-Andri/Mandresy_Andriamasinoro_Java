package com.company.Summative2AndriamasinoroMandresy.controllers;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import com.company.Summative2AndriamasinoroMandresy.models.Book;
import com.company.Summative2AndriamasinoroMandresy.models.Publisher;
import com.company.Summative2AndriamasinoroMandresy.repositories.AuthorRepository;
import com.company.Summative2AndriamasinoroMandresy.repositories.BookRepository;
import com.company.Summative2AndriamasinoroMandresy.repositories.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //Setting up mock repos
    @MockBean
    private BookRepository bookRepo;
    @MockBean
    private PublisherRepository publishRepo;
    @MockBean
    private AuthorRepository authRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private Book book;
    private String bookJson;
    private List<Book> allBook = new ArrayList<>();
    private String allBookJson;

    @Before
    public void setup() throws Exception {
        //add module to mapper, so it can change LocalDate to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //add new publisher
        Publisher publish1 = new Publisher();
        publish1.setName("Banjo");
        publish1.setStreet("Croissant st");
        publish1.setCity("Alexandria");
        publish1.setState("AL");
        publish1.setPostal_code("73194");
        publish1.setPhone("4568762014");
        publish1.setEmail("baloo@gmail.com");

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

        //add book written by author
        book = new Book();
        book.setIsbn("1852860820");
        book.setPublish_date(LocalDate.of(1986,5,6));
        book.setAuthorId(author1.getId());
        book.setTitle("Eagleman vs Greatman");
        book.setPublisherId(publish1.getId());
        book.setPrice(BigDecimal.valueOf(259.99));

        //change book to JSON
        bookJson = mapper.writeValueAsString(book);

        //add book to list
        allBook.add(book);

        //change list to JSON
        allBookJson = mapper.writeValueAsString(allBook);
    }


    // Testing GET
    @Test
    public void shouldReturnAllBooks() throws Exception {
        doReturn(allBook).when(bookRepo).findAll();

        mockMvc.perform(
                        get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(allBookJson)
                );
    }

    // Testing GET by id
    @Test
    public void shouldReturnBookById() throws Exception {
        doReturn(Optional.of(book)).when(bookRepo).findById(book.getId());

        ResultActions result = mockMvc.perform(
                        get("/books/{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect((content().json(bookJson))
                );
    }

    //Testing POST
    @Test
    public void shouldCreateBookOnPostRequest() throws Exception {
        //add new publisher
        Publisher publish1 = new Publisher();
        publish1.setName("Banjo");
        publish1.setStreet("Croissant st");
        publish1.setCity("Alexandria");
        publish1.setState("AL");
        publish1.setPostal_code("73194");
        publish1.setPhone("4568762014");
        publish1.setEmail("baloo@gmail.com");

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

        //add book written by author
        Book inputBook = new Book();
        book.setIsbn("1852860820");
        book.setPublish_date(LocalDate.of(1986,5,6));
        book.setAuthorId(author1.getId());
        book.setTitle("Eagleman vs Greatman");
        book.setPublisherId(publish1.getId());
        book.setPrice(BigDecimal.valueOf(259.99));

        //change to Json input to test Post
        String inputJson = mapper.writeValueAsString(inputBook);

        //It should return banjo when repo saves inputPublisher
        doReturn(book).when(bookRepo).save(inputBook);
        //perform post
        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(bookJson));

    }

    // Testing PUT
    @Test
    public void shouldUpdate() throws Exception {
        doReturn(book).when(bookRepo).save(book);
        mockMvc.perform(
                        put("/books")
                                .content(bookJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    //Testing Delete
    @Test
    public void shouldDeleteById() throws Exception {
        doNothing().when(bookRepo).deleteById(book.getId());
        mockMvc.perform(delete("/books/{id}", book.getId()))
                .andExpect(status().isNoContent());
    }
}

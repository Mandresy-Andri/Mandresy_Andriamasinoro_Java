package com.company.Summative2AndriamasinoroMandresy.controllers;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import com.company.Summative2AndriamasinoroMandresy.repositories.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Author banjo;
    private String banjoJson;
    private List<Author> allAuthors = new ArrayList<>();
    private String allAuthorsJson;
    @Before
    public void setup() throws Exception {
        //add new author
        banjo = new Author();
        banjo.setFirstName("Banjo");
        banjo.setLast_name("Bilbaolin");
        banjo.setStreet("Croissant st");
        banjo.setCity("Alexandria");
        banjo.setState("AL");
        banjo.setPostal_code("73194");
        banjo.setPhone("4568762014");
        banjo.setEmail("baloo@gmail.com");

        banjoJson = mapper.writeValueAsString(banjo);

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

        //add authors to list
        allAuthors.add(banjo);
        allAuthors.add(author1);

        //change list to JSON
        allAuthorsJson = mapper.writeValueAsString(allAuthors);
    }


    // Testing GET
    @Test
    public void shouldReturnAllAuthors() throws Exception {
        doReturn(allAuthors).when(repo).findAll();

        mockMvc.perform(
                        get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(allAuthorsJson)
                );
    }

    // Testing GET by id
    @Test
    public void shouldReturnAuthorById() throws Exception {
        doReturn(Optional.of(banjo)).when(repo).findById(banjo.getId());

        ResultActions result = mockMvc.perform(
                        get("/authors/{id}",banjo.getId()))
                .andExpect(status().isOk())
                .andExpect((content().json(banjoJson))
                );
    }

    //Testing POST
    @Test
    public void shouldCreateAuthorOnPostRequest() throws Exception {
        //create new author
        Author inputAuthor = new Author();
        inputAuthor.setFirstName("Banjo");
        inputAuthor.setLast_name("Bilbaolin");
        inputAuthor.setStreet("Croissant st");
        inputAuthor.setCity("Alexandria");
        inputAuthor.setState("AL");
        inputAuthor.setPostal_code("73194");
        inputAuthor.setPhone("4568762014");
        inputAuthor.setEmail("baloo@gmail.com");

        //change to Json input to test Post
        String inputJson = mapper.writeValueAsString(inputAuthor);

        //It should return banjo when repo saves inputAuthor
        doReturn(banjo).when(repo).save(inputAuthor);

        //perform post
        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(banjoJson));

    }

    //Testing PUT
    @Test
    public void shouldUpdate() throws Exception {
        doReturn(banjo).when(repo).save(banjo);
        mockMvc.perform(
                        put("/authors")
                                .content(banjoJson)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }

    //Testing DELETE
    @Test
    public void shouldDeleteById() throws Exception {
        doNothing().when(repo).deleteById(banjo.getId());
        mockMvc.perform(delete("/authors/{id}",banjo.getId()))
                .andExpect(status().isNoContent());
    }
}

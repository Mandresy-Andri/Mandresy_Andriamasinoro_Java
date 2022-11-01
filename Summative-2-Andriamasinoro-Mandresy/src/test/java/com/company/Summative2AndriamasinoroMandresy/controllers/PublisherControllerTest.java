package com.company.Summative2AndriamasinoroMandresy.controllers;

import com.company.Summative2AndriamasinoroMandresy.models.Publisher;
import com.company.Summative2AndriamasinoroMandresy.repositories.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
@WebMvcTest(PublisherController.class)
@Import(PublisherController.class)
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Publisher banjo;
    private String banjoJson;
    private List<Publisher> allPublishers = new ArrayList<>();
    private String allPublisherJson;

    @Before
    public void setup() throws Exception {
        //add new publisher
        banjo = new Publisher();
        banjo.setName("Banjo");
        banjo.setStreet("Croissant st");
        banjo.setCity("Alexandria");
        banjo.setState("AL");
        banjo.setPostal_code("73194");
        banjo.setPhone("4568762014");
        banjo.setEmail("baloo@gmail.com");

        banjoJson = mapper.writeValueAsString(banjo);

        //add new publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Cris");
        publisher1.setStreet("Real st");
        publisher1.setCity("Madrida");
        publisher1.setState("NV");
        publisher1.setPostal_code("4698");
        publisher1.setPhone("5423010259");
        publisher1.setEmail("cr8@gmail.com");

        allPublishers.add(banjo);
        allPublishers.add(publisher1);

        allPublisherJson = mapper.writeValueAsString(allPublishers);
    }


    // Testing GET
    @Test
    public void shouldReturnAllPublishers() throws Exception {
        doReturn(allPublishers).when(repo).findAll();

        mockMvc.perform(
                        get("/publishers"))
                .andExpect(status().isOk())
                .andExpect(content().json(allPublisherJson)
                );
    }

    // Testing GET by id
    @Test
    public void shouldReturnPublisherById() throws Exception {
        doReturn(Optional.of(banjo)).when(repo).findById(banjo.getId());

        ResultActions result = mockMvc.perform(
                        get("/publishers/{id}", banjo.getId()))
                .andExpect(status().isOk())
                .andExpect((content().json(banjoJson))
                );
    }

    //Testing POST
    @Test
    public void shouldCreatePublisherOnPostRequest() throws Exception {
        //add new publisher
        Publisher inputPublisher = new Publisher();
        inputPublisher.setName("Banjo");
        inputPublisher.setStreet("Croissant st");
        inputPublisher.setCity("Alexandria");
        inputPublisher.setState("AL");
        inputPublisher.setPostal_code("73194");
        inputPublisher.setPhone("4568762014");
        inputPublisher.setEmail("baloo@gmail.com");
        //change to Json input to test Post
        String inputJson = mapper.writeValueAsString(inputPublisher);
        //It should return banjo when repo saves inputPublisher
        doReturn(banjo).when(repo).save(inputPublisher);
        //perform post
        mockMvc.perform(
                        post("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(banjoJson));

    }

    // Testing PUT
    @Test
    public void shouldUpdate() throws Exception {
        doReturn(banjo).when(repo).save(banjo);
        mockMvc.perform(
                        put("/publishers")
                                .content(banjoJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    //Testing Delete
    @Test
    public void shouldDeleteById() throws Exception {
        doNothing().when(repo).deleteById(banjo.getId());
        mockMvc.perform(delete("/publishers/{id}", banjo.getId()))
                .andExpect(status().isNoContent());
    }
}

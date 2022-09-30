package com.company.Summative1AndriamasinoroMandresy.controllers;

import com.company.Summative1AndriamasinoroMandresy.models.Answer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Magic8BallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateAnswerWithUserQuestion() throws Exception {
        //Create Answer with a question in it
        Answer answer = new Answer();
        answer.setQuestion("Will we have a new season of Cobra Kai?");
        //Change answer to json
        String inputJson = mapper.writeValueAsString(answer);
        //perform the test
        mockMvc.perform(post("/magic")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateAnswerWithoutUserQuestion() throws Exception {
        //Create Answer with no question in it
        Answer answer = new Answer();
        //Change answer to json
        String inputJson = mapper.writeValueAsString(answer);
        //perform the test
        mockMvc.perform(post("/magic")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}

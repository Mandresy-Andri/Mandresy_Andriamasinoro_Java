package com.company.Summative1AndriamasinoroMandresy.controllers;

import com.company.Summative1AndriamasinoroMandresy.models.Answer;
import com.company.Summative1AndriamasinoroMandresy.models.Definition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class Magic8BallController {

    //List that holds all possible responses
    private List<Answer> answerList;

    private static int idCounter=1;

    public Magic8BallController(){
        answerList = new ArrayList<>();

        answerList.add(new Answer("Maybe",idCounter++));
        answerList.add(new Answer("That will definitely happen",idCounter++));
        answerList.add(new Answer("No",idCounter++));
        answerList.add(new Answer("Yes",idCounter++));
        answerList.add(new Answer("Never in a million years",idCounter++));
        answerList.add(new Answer("No comment",idCounter++));
    }

    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer createAnswer(@RequestBody (required = false) Answer answer){
        //generate random int ranging from 0-6
        Random rand = new Random();
        int random = rand.nextInt(6);

        //add a random response to the answer object
        answer.setAnswer(answerList.get(random).getAnswer());

        return answer;
    }
}

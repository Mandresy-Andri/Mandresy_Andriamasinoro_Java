package com.company.Summative1AndriamasinoroMandresy.controllers;

import com.company.Summative1AndriamasinoroMandresy.models.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {

    //List with all quotes
    private List<Quote> quoteList;
    private static int idCounter=1;

    public QuoteController(){
        quoteList = new ArrayList<>();

        //Quotes taken from this website: https://kidadl.com/quotes/top-most-inspirational-superhero-quotes-all-young-fans-should-know
        quoteList.add(new Quote("Batman","I wear a mask, and that mask is not to hide who I am, but to create who I am.",idCounter++));
        quoteList.add(new Quote("Daredevil","Violence doesn’t discriminate. It comes as cold and bracing as a winter breeze and it leaves you with a chill you can’t shake off.",idCounter++));
        quoteList.add(new Quote("Thor","The fate of your planet rests not in the hands of gods. It rests in the hands of mortals.",idCounter++));
        quoteList.add(new Quote("Iron Man","Heroes are made by the path they choose, not the powers they are graced with.",idCounter++));
        quoteList.add(new Quote("Captain America","The strength of this country isn’t in buildings of brick and steel. It’s in the hearts of those who have sworn to fight for its freedom!",idCounter++));
        quoteList.add(new Quote("Spiderman","No man can win every battle, but no man should fall without a struggle.",idCounter++));
        quoteList.add(new Quote("Uncle Ben","Remember, with great power comes great responsibility.",idCounter++));
        quoteList.add(new Quote("Wonder Woman","Only love can save the world. So I stay. I fight, and I give... for the world I know can be. This is my mission, now. Forever.",idCounter++));
        quoteList.add(new Quote("Alfred Pennyworth","Why do we fall, sir? So that we can learn to pick ourselves up.",idCounter++));
        quoteList.add(new Quote("Hulk","Hulk Smash!",idCounter++));
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Quote getQuote(){
        //generate random int ranging from 0-9
        Random rand = new Random();
        int random = rand.nextInt(10);

        //return random quote
        return quoteList.get(random);
    }
}

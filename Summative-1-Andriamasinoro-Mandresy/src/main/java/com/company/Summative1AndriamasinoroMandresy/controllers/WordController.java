package com.company.Summative1AndriamasinoroMandresy.controllers;

import com.company.Summative1AndriamasinoroMandresy.models.Definition;
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
public class WordController {

    //List of all Definitions
    private List<Definition> definitionList;

    private static int idCounter=1;

    public WordController(){
        definitionList = new ArrayList<>();

        //random definition generated from this website: https://randomword.com/
        definitionList.add(new Definition("Velometer","instrument for measuring speed of air",idCounter++));
        definitionList.add(new Definition("quenouille","concerning the training of trees into cone-like shape",idCounter++));
        definitionList.add(new Definition("fandangle","pretentious tomfoolery",idCounter++));
        definitionList.add(new Definition("amphigory","a nonsense verse",idCounter++));
        definitionList.add(new Definition("diplocephalus","two-headed monster",idCounter++));
        definitionList.add(new Definition("topaesthesia","determining place by sense of touch",idCounter++));
        definitionList.add(new Definition("trireme","ancient ship with three banks of oars",idCounter++));
        definitionList.add(new Definition("xiphoid","sword-shaped",idCounter++));
        definitionList.add(new Definition("yordim","emigrants who leave Israel",idCounter++));
        definitionList.add(new Definition("discombobulate","to disconcert; to upset",idCounter++));
    }

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Definition getDefinition(){
        //generate random int ranging from 0-9
        Random rand = new Random();
        int random = rand.nextInt(10);

        //return a random definition
        return definitionList.get(random);
    }
}

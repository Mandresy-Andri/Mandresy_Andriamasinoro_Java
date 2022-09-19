package com.example.utilityapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    public CalculatorController() {
    }

    @RequestMapping(value = "/calculator/divide", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public double divide(@RequestParam int value1, @RequestParam int value2) {
        if(value2==0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return value1 / value2;
    }

    @RequestMapping(value = "/calculator/square/{value}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public int square(@PathVariable int value) {
        //greatest int allowed is 2147483647 so the max value given here should be 46340
        if(value>46340){
            throw new IllegalArgumentException("Value is too large");
        }
        return value * value;
    }
}

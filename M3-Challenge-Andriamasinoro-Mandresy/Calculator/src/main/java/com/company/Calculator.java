package com.company;

public class Calculator {

    public int divide(int a, int b) {
        if ( b == 0 ) {
            return 0;
        }

        return a/b;
    }

    public int add(int a, int b){
        return a+b;
    }

    public int subtract(int a, int b){
        return a-b;
    }

    public int multiply(int a, int b){
        return a*b;
    }
}

package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    //DIVISION///////////////////////////////////////////////////////////////////////////////////
    @Test
    public void shouldDividePositives() {

        assertEquals(3, calc.divide(6,2));
        assertEquals(2, calc.divide(80, 39));
        assertEquals(1, calc.divide(105, 100));
    }

    @Test
    public void shouldDivideNegatives() {

        assertEquals(3, calc.divide(-6,-2));
        assertEquals(2, calc.divide(-80, -39));
        assertEquals(1, calc.divide(-105, -100));
    }

    @Test
    public void shouldDivideBothPosAndNeg() {
        assertEquals(-3, calc.divide(6,-2));
        assertEquals(-2, calc.divide(-80, 39));
        assertEquals(-1, calc.divide(105, -100));
    }

    @Test
    public void shouldReturnZero() {
        assertEquals(0, calc.divide(0,0));
        assertEquals(0, calc.divide(0, 1));
        assertEquals(0, calc.divide(105, 0));
    }


    //ADDITION///////////////////////////////////////////////////////////////////////////////////
    @Test
    public void addPositives() {
        assertEquals(10, calc.add(5,5));
        assertEquals(5, calc.add(2, 3));
        assertEquals(20, calc.add(17, 3));
    }

    @Test
    public void addNegatives() {
        assertEquals(-10, calc.add(-5,-5));
        assertEquals(-5, calc.add(-2, -3));
        assertEquals(-20, calc.add(-17, -3));
    }

    @Test
    public void addPosAndNeg() {
        assertEquals(-10, calc.add(10,-20));
        assertEquals(5, calc.add(-15, 20));
        assertEquals(20, calc.add(-5, 25));
    }

    @Test
    public void addToZero() {
        assertEquals(0, calc.add(0,0));
        assertEquals(0, calc.add(-5, 5));
        assertEquals(0, calc.add(42, -42));
    }


    //SUBTRACTION///////////////////////////////////////////////////////////////////////////////////
    @Test
    public void subtractPositives() {
        assertEquals(10, calc.subtract(20,10));
        assertEquals(-5, calc.subtract(10, 15));
        assertEquals(20, calc.subtract(23, 3));
    }

    @Test
    public void subtractNegatives() {
        assertEquals(5, calc.subtract(-5,-10));
        assertEquals(-10, calc.subtract(-15, -5));
        assertEquals(-20, calc.subtract(-30, -10));
    }

    @Test
    public void subtractPosAndNeg() {
        assertEquals(30, calc.subtract(10,-20));
        assertEquals(-35, calc.subtract(-15, 20));
        assertEquals(-24, calc.subtract(-4, 20));
    }

    @Test
    public void subtractToZero() {
        assertEquals(0, calc.subtract(0,0));
        assertEquals(0, calc.subtract(5, 5));
    }


    //MULTIPLICATION///////////////////////////////////////////////////////////////////////////////////
    @Test
    public void multiplyPositives() {
    assertEquals(25, calc.multiply(5,5));
    assertEquals(6, calc.multiply(2, 3));
    assertEquals(21, calc.multiply(7, 3));
}

    @Test
    public void multiplyNegatives() {
        assertEquals(25, calc.multiply(-5,-5));
        assertEquals(6, calc.multiply(-2, -3));
        assertEquals(21, calc.multiply(-7, -3));
    }

    @Test
    public void multiplyPosAndNeg() {
        assertEquals(-15, calc.multiply(3,-5));
        assertEquals(-20, calc.multiply(-4, 5));
        assertEquals(-80, calc.multiply(-4, 20));
    }

    @Test
    public void multiplyToZero() {
        assertEquals(0, calc.multiply(0,0));
        assertEquals(0, calc.multiply(5, 0));
        assertEquals(0, calc.multiply(-5, 0));
    }
}

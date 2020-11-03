package org.example;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringCalculatorTest {

    // Make sure you only test for correct inputs. there is no need to test for invalid inputs for this kata

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // To not repeat code, create a method that handles the initialisation of the StringCalculator object
    // and the calculation of the result, and the assertion.
    public void commonAssertions(String numbers, int expected){
        StringCalculator sc = new StringCalculator();
        int result = sc.add(numbers);
        assertEquals(expected, result);
    }

    // To test for negative numbers.
    public StringCalculator instantiateCalc(){
        return new StringCalculator();
    }

    @Test
    public void addEmptyString_ReturnsZero(){
        commonAssertions("", 0);
    }

    @Test
    public void addSingleNumber_ReturnsThatNumber(){
        commonAssertions("1", 1);
    }

    @Test
    public void addMultipleNumbers_ReturnsSum(){
        commonAssertions("1,2,7", 10);
    }

    @Test
    public void addNumbersWithNewLine_ReturnsSum(){
        commonAssertions("1\n2,3", 6);
    }

    @Test
    public void addNumbersWithCustomDelimiters_ReturnsSum(){
        commonAssertions("//;\n1;2;8",11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNumberWithOneNegative_ThrowsExceptionWithNumber(){
        instantiateCalc().add("//;\n1;2;-8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNumberWithManyNegatives_ThrowsExceptionWithNumbers(){
        instantiateCalc().add("//;\n-1;2;-8");
    }

    @Test
    public void addNumbersOverAThousand_IgnoresThousands(){
        commonAssertions("4,5\n2021,1999\n1",10);
    }

    @Test
    public void addNumbersDelimiterOfAnyLength_ReturnsSum(){
        commonAssertions("//[***]\n1***2***3", 6);
    }

    @Test
    public void addNumbersMultipleDelimiters_ReturnsSum(){
        commonAssertions("//[*][%]\n1*2%3", 6);
    }

    @Test
    public void addNumbersMultipleDelimitersAnyLength_ReturnsSum(){
        commonAssertions("//[**][%%]\n1**2%%3", 6);
    }

    @Test
    public void addNumbersMultipleDelimitersAnyLength_ReturnsSum_custom(){
        commonAssertions("//[=&=#][%%]\n1=&=#2%%3", 6);
    }

    @Test
    public void getCalledCount(){
        assertEquals(12, StringCalculator.getCalledCount());
    }

}
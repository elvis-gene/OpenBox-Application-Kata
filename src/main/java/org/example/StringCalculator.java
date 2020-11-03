/*
    Author: Elvis Gene
    Date: 03-11-2020
    Description: OpenBox application kata
 */


/*
    Note: I did everything except question 8 that is for .Net only

    Algorithm For questions 10, 11, & 12

        - Check if the input contains "//[
        - If it does, count the number of open brackets [.
            The number of open brackets will correspond to the number of delimiters
        - Using a for loop with a runtime dependent on the number open brackets/delimiters, save all delimiters in a list
        - Remove a delimiter from the input once it is added to the list
        - Join all the delimiters from the list into one string separating them with a |
        - Then calculate
 */

package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {
    private static int calledCount = 0;

    public int add(String numbers){
        calledCount++;

        // Test for an empty string
        if (isEmptyString(numbers))
            return 0;
        // The call below is not mandatory. A string will only one number could be handled by the
        // calculateSumOfMultipleNumbers method as well but I prefer to have the method isSingleNumber to save runtime
        if (isSingleNumber(numbers))
            return Integer.parseInt(numbers);
        else
            return calculateSumOfMultipleNumbers(numbers);
    }

    public int calculateSumOfMultipleNumbers(String numbers){

        if (numbers.startsWith("//")) {

            long openBracketCounter = numbers.chars().filter(ch -> ch == '[').count();
            ArrayList<String> delimiters = new ArrayList<>();
            String currentDelimiterAndItsBrackets = "";

            // For 10, 11, & 12
            if (numbers.contains("//[")){
                for (int i = 1; i <= openBracketCounter; i++){
                    currentDelimiterAndItsBrackets = numbers.substring(numbers.indexOf('['), numbers.indexOf(']') + 1);

                    // Pattern.quote to put all the characters of a delimiter together and escape special characters
                    delimiters.add(Pattern.quote(numbers.substring(numbers.indexOf('[') + 1, numbers.indexOf(']'))));

                    numbers = numbers.replace(currentDelimiterAndItsBrackets, "");
                }
                String[] textBeforeAndAfterNewLine = numbers.split("\n");
                numbers = textBeforeAndAfterNewLine[1];

                return getSum(String.join("|", delimiters), numbers);
            }
            else {
                // For 4.
                String[] textBeforeAndAfterNewLine = numbers.split("\n");
                String textBeforeNewLine = textBeforeAndAfterNewLine[0];
                String delimiter = Character.toString(textBeforeNewLine.charAt(textBeforeNewLine.length() - 1));
                numbers = textBeforeAndAfterNewLine[1];

                return getSum(delimiter, numbers);
            }
        }else
            // If the string numbers doesn't start with //, then delimiters are either commas(,) or a new lines (\n)
            return getSum("[,\n]", numbers);
    }

    public int getSum(String delimiter, String numbers){

        int [] numbersArray = Arrays.stream(numbers.split(delimiter)).mapToInt(Integer :: parseInt).toArray();
        ArrayList<String> negativeNumbers = new ArrayList<>();
        int sum = 0;

        for (int num : numbersArray){
            if (num < 0)
                negativeNumbers.add(Integer.toString(num));
            else
                sum += (num < 1001)? num : 0;
        }
        if (negativeNumbers.size() == 0)
            return sum;
        else
            throw  new IllegalArgumentException("Negatives not allowed but found: "+ String.join(", ", negativeNumbers));
    }

    public boolean isEmptyString(String numbers){
        return numbers.isEmpty();
    }

    public boolean isSingleNumber(String numbers){
        return numbers.length() == 1;
    }

    public static int getCalledCount(){
        return calledCount;
    }

}

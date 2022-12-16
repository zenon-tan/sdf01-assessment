package task02;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final String ADD = "+";
    public static final String MINUS = "-";
    public static final String MULTI = "*";
    public static final String DIVIDE = "/";
    public static final List<String> OPERATORS = Arrays.asList(ADD, MINUS, MULTI, DIVIDE);
    public static final String INTREGEX = "\\d+";

    public static void main(String[] args) {

        int $last = 0;

        Boolean isQuit = false;

        Console cons = System.console();

        System.out.println("Welcome.");

        while(!isQuit) {

            String input = cons.readLine("> ");
            String[] splitString = input.split(" ");

            if(splitString.length == 1 && splitString[0].equals("exit")) {

                isQuit = true;
                System.out.println("Bye bye");

            } else if(splitString.length < 3 && !splitString[0].equals("exit")) {

                System.out.println("Invalid input");

            } else {

                String firstNum = splitString[0];
                String secondNum = splitString[2];
                String operator = splitString[1];

                if(OPERATORS.contains(operator)) {

                    int $newlast = 0;

                    if(firstNum.matches(INTREGEX) && secondNum.matches(INTREGEX)) {

                        int firstInt = Integer.parseInt(firstNum);
                        int secondInt = Integer.parseInt(secondNum);

                        $last = performCalculation(firstInt, operator, secondInt);

                    } else if(firstNum.matches(INTREGEX) && secondNum.equals("$last")) {

                        int firstInt = Integer.parseInt(firstNum);
                        $newlast = performCalculation(firstInt, operator, $last);
                        $last = $newlast;

                    } else if(firstNum.equals("$last") && secondNum.matches(INTREGEX)) {

                        int secondInt = Integer.parseInt(secondNum);
                        $newlast = performCalculation($last, operator, secondInt);
                        $last = $newlast;

                    }
                     else if(firstNum.equals("$last") && secondNum.equals("$last")) {

                        int firstLast = $last;
                        int secondLast = $last;

                        $newlast = performCalculation(firstLast, operator, secondLast);
                        $last = $newlast;
                    } 

                    System.out.println($last);

                } else {

                    System.out.println("Please key in a valid operator (+,-,*,/)");
                }


            }

            
        }


    } public static int performCalculation(int first, String operator, int second) {

        int sum = 0;

        switch(operator) {

            case(ADD):
                sum = first + second;
                break;
            case(MINUS):
                sum = first - second;
                break;
            case(MULTI):
                sum = first * second;
                break;
            case(DIVIDE):
                sum = first / second;
                break;
            default:
                break;
        }

        return sum;
    }
    
}

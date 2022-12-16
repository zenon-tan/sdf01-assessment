package task02.src;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

public class Main {

    // Constants
    public static final String ADD = "+";
    public static final String MINUS = "-";
    public static final String MULTI = "*";
    public static final String DIVIDE = "/";
    public static final List<String> OPERATORS = Arrays.asList(ADD, MINUS, MULTI, DIVIDE);

    public static void main(String[] args) {

        float $last = 0;

        Boolean isQuit = false;

        Console cons = System.console();

        System.out.println("Welcome.");

        while(!isQuit) {

            String input = cons.readLine("> ");
            String[] splitString = input.split(" ");

            // if exit is typed

            if(splitString.length >= 1 && splitString[0].strip().equals("exit")) {

                isQuit = true;
                System.out.println("Bye bye");

            // if gibberish is typed or format is wrong

            } else if(splitString.length != 3 && (!splitString[0].strip().equals("exit") && !splitString[0].equals("$last"))) {

                System.out.println("Invalid input");

            } else {

                // if format is correct

                String firstNum = splitString[0].strip();
                String secondNum = splitString[2].strip();
                String operator = splitString[1].strip();

                if(!firstNum.equals("$last") && !secondNum.equals("$last")) {

                    try {

                    float firstVal = Float.parseFloat(firstNum);
                    float secondVal = Float.parseFloat(secondNum);

                    float $newlast = performCalculation(firstVal, operator, secondVal);

                    $last = $newlast;
                    
                        
                    } catch (Exception e) {
                        System.out.println("Invalid number");
                    }

                        
                } else if(secondNum.equals("$last") && !firstNum.equals("$last")) {

                    try {
                        float firstVal = Float.parseFloat(firstNum);
                        $last = performCalculation(firstVal, operator, $last);
                        
                    } catch (Exception e) {
                        System.out.println("Invalid number");
                    }

                } else if(firstNum.equals("$last") && !secondNum.equals("$last")) {

                    try {

                        float secondVal = Float.parseFloat(secondNum);
                        $last = performCalculation($last, operator, secondVal);
                        
                        
                    } catch (Exception e) {
                        System.out.println("Invalid number");
                    }
                } else if(firstNum.equals("$last") && secondNum.equals("$last")){

                    float firstLast = $last;
                    float secondLast = $last;
    
                    $last = performCalculation(firstLast, operator, secondLast);



                }

                if($last == (int)$last) {

                    System.out.println((int)$last);
    
                } else {
    
                    System.out.println($last);
    
                }

            
            }

            

      
        }

    } 

    // Method to perform calcuation
    
    public static float performCalculation(float first, String operator, float second) {

        float sum = 0f;

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

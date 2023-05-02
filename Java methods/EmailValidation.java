/*Create a function that accepts a string, checks if it's a valid email address and returns either true or false, depending on the evaluation.

The string must contain an @ character.
The string must contain a . character.
The @ must have at least one character in front of it.
e.g. "e@edabit.com" is valid while "@edabit.com" is invalid.
The . and the @ must be in the appropriate places.
e.g. "hello.email@com" is invalid while "john.smith@email.com" is valid.
If the string passes these tests, it's considered a valid email address.

validateEmail("@gmail.com") ➞ false

validateEmail("hello.gmail@com") ➞ false

validateEmail("gmail") ➞ false

validateEmail("hello@gmail") ➞ false

validateEmail("hello@edabit.com") ➞ true 

*/
import java.util.*;

public class EmailValidation {
    private static Scanner input = new Scanner(System.in);
    public static void main (String[] args){
        System.out.println("Type '0' to exit.");
        while (true){
            
            System.out.println("Check email: ");
            String email = input.nextLine();
            boolean result = emailCheck(email);
            System.out.println(result);

            if (email.equals("0")){        
                System.exit(0);
        }
    }
}
    public static boolean emailCheck(String email){
        String reverse = new StringBuffer(email).reverse().toString();
        int atIndex =0;
        int dotIndex =0;
        boolean testTrue = false;

//The string must contain an @ character.
//No need to iterate.. email.contains("@");       
        for (int i = 0; i<reverse.length();i++){
            if (reverse.substring(i, i+1).equals("@")){
                atIndex = i;
                if (i == reverse.length()-1){
                    System.out.println("Can't start with '@' mark");
                   testTrue = false;
                   break;
                }
                //The @ must have at least one character in front of it.
              if (reverse.substring(i+1, i+2).matches(".*[a-z].*")) { 
                    testTrue = true;
                    break;
                } 
            }
        }
//The string must contain a . character.
//No need to iterate.. reverse.contains(".");   
        if (testTrue == true){
            testTrue = false;
            for (int i = 0; i<reverse.length();i++){
                if (reverse.substring(i, i+1).equals(".")){
                    dotIndex = i;
                    testTrue = true;
                    break;
                }          
            } 
        }
//The . and the @ must be in the appropriate places.
        if (atIndex > dotIndex && testTrue ==true){
            testTrue =true;
        }
        return testTrue;
    }
}

/*
Notes
Check the Tests tab to see exactly what's being evaluated.
You can solve this challenge with RegEx, but it's intended to be solved with logic.
Solutions using RegEx will be accepted but frowned upon :( */
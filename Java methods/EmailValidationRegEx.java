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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationRegEx {
    private static Scanner input = new Scanner(System.in);
    public static void main (String[] args){
        System.out.println("Type '0' to exit.");
        while (true){
            
            System.out.println("Check email: ");
            String email = input.nextLine();
            
            if (email.equals("0")){        
                System.exit(0);
            }    
            boolean result = emailCheck(email);
            System.out.println(result);
            System.out.println("------------------------");

        }
    }

    public static boolean emailCheck(String email){
        boolean check = false;
        String regex1 = "@";
        String regex2 = "([.])"; 

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher = pattern1.matcher(email);
        Matcher matcher2 = pattern2.matcher(email);
       
    //FIND IF THERE IS @ IN EMAIL
        while(matcher.find()){
            String matchedValue1 = matcher.group();
            int atIndex = matcher.start();
           // System.out.printf("Matched startIndex= %s, endIndex= %s, match: '%s'\n",
           //             matcher.start(), matcher.end(), matchedValue1);
           System.out.println("matchedvalue1 ="+matchedValue1);
           System.out.println("atindex = "+atIndex);
        }
    //FIND IF THERE IS . IN EMAIL      
        while(matcher2.find()){
            String matchedValue2 = matcher2.group();
            int dotIndex = matcher2.start();
            //   System.out.printf("Matched startIndex= %s, endIndex= %s, match: '%s'\n",
            //               matcher.start(), matcher.end(), matchedValue2);
            System.out.println("matchedvalue2 =" +matchedValue2);
            System.out.println("dotindex = "+dotIndex);
            }
          
            
        return check;
        }
        
        
    }


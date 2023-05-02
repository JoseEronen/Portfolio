/*Create a function that will test if a string is a valid PIN 
or not via a regular expression.

A valid PIN has:

Exactly 4 or 6 characters.
Only numeric characters (0-9).
No whitespace. */

public class ValidatePIN {
    public static void main (String[]args){
        boolean result = checkPIN("   ");
        System.out.println(result);
    }

        public static boolean checkPIN(String pin) {
        
            String pattern4 = "\\d\\d\\d\\d";
            String pattern6 = "\\d\\d\\d\\d\\d\\d";
            boolean check = false;


            //Check if pin is empty
            check = pin.isEmpty();
            if (check ==true){
                
            return false;
                           }
            //Check if pin has whitespace
            char[] charArr = pin.toCharArray();

            for (int i = 0;i<charArr.length;i++){
                if (Character.isWhitespace(charArr[i])){
                    check = false;
                    return false;
                }
            }

            //Check if pin has 4 digits
            check = pin.matches(pattern4);
            if (check ==true){
                return true;
            }
            
            //Check if pin has 6 digits
            check = pin.matches(pattern6);
            if (check ==true){
                return true;
            }
            return check;
        }
      
}

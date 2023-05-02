
import java.util.*;
public class AddingStringNumbers {
    private static Scanner input = new Scanner(System.in);
    public static void main (String[] args){
        
        System.out.println("Enter 1. number");
        String s1 = input.nextLine();
        System.out.println("Enter 2. number");
        String s2 = input.nextLine();
        String result = addingNumbers(s1, s2);
        System.out.println(result);
    }
    public static String addingNumbers(String s1, String s2){
        int n1 = Integer.parseInt(s1);
        int n2 = Integer.parseInt(s2);
        int sum = n1+n2;
        String sumString = Integer.toString(sum);
        
        return "Result: "+sumString; 
    }
}

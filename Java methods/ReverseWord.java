
//Function reverse injected word.

public class ReverseWord {
    public static void main (String[] args){
        reverseWord("AUTS");

    }
public static String reverseWord(String word){
    String reversedWord ="";
    for (int i = word.length();i>=0;i--){
       reversedWord = word.substring(i);
       System.out.println(reversedWord);
    }
    
    return reversedWord;
} 
}

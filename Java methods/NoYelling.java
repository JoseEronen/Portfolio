import java.util.*;

/*Create a function that transforms sentences ending with 
multiple question marks ? or exclamation marks ! into a 
sentence only ending with one without changing punctuation in 
the middle of the sentences. */

public class NoYelling {

    public static void main (String[]args) {
        String result = noYelling("LOL!!! OMG!! WTH????!!!");
        System.out.println(result);
    }

    public static String noYelling (String sentence){
        String[] arrOfStrings = sentence.split(" ",0);
        char[] lastWordCheck = arrOfStrings[arrOfStrings.length-1].toCharArray();

        int sum=0;
        for (int i = 0; i<lastWordCheck.length;i++){
            if(Character.toString(lastWordCheck[i]).equals("!")||Character.toString(lastWordCheck[i]).equals("?")){
                sum++;
            }
        }
        String[] firstWords = new String[arrOfStrings.length-1];
        for (int i = 0;i<arrOfStrings.length-1;i++){
            firstWords[i] = arrOfStrings[i];
        }
//Poistetaan ylimääräiset merkit sanan lopusta
        char[] fixedLastWord = Arrays.copyOfRange(lastWordCheck, 0, lastWordCheck.length-sum+1);
        
        String lastWord = new String(fixedLastWord);
        String words = String.join(" ",firstWords);
        String fixedSentence = words+" "+lastWord;
  
        return fixedSentence;
    }

}


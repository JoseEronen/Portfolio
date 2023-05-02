import java.util.*;

public class LongestWord {
    public static Scanner input = new Scanner(System.in);

    public static void main (String[]args){
        System.out.println("Type sentence..");
        String sentence = input.nextLine();
        String result = longestWord(sentence);
        System.out.println(result);
    }

	public static String longestWord(String phrase) {
        System.out.println("-------ANALYSING---------");
        System.out.println("Inserted: " +phrase);
        ArrayList<String> wordList = new ArrayList<String>();

        for (String returnValue: phrase.split(" ", 0)){
            wordList.add(returnValue);
        }
        System.out.println("Words in sentence: " +wordList);

        ArrayList<Integer> wordLength = new ArrayList<Integer>();
        for (int i =0; i<wordList.size();i++){
            int num = wordList.get(i).length();
            wordLength.add(num);
        }
        System.out.println("Word lenght: "+ wordLength);
        int max = Collections.max(wordLength);
        int indexPosition = wordLength.indexOf(max);
        System.out.println("Longest word: "+wordList.get(indexPosition)+". Word has "+ max+" letters.");
        System.out.println("--------END OF PROGRAM------");
        return wordList.get(indexPosition);
	}
}

/* This function finds the difference between two strings */


public class FindDifference {
    
    public static void main (String[]args){
        findDifference("Learning java is fan", "Learning java is fun");
    }
    public static String findDifference(String a, String b){
            String longWord;
            String shortWord;
        if (a.length() < b.length()){
            longWord =b;
            shortWord = a;
        } else{
            longWord = a;
            shortWord =b;
        }

        for (int i = 0; i<shortWord.length(); i++){
            if (longWord.substring(i,i+1).equals(shortWord.substring(i,i+1))){
                continue;
            } else {
                System.out.println("Difference found at index: " +i);
                System.out.println(longWord.substring(i,i+1) + " != "+ shortWord.substring(i, i+1));
            }
        }
        for (int i = shortWord.length();i<longWord.length();i++){
            System.out.println("Difference found at index "+i);
            System.out.println(longWord.substring(i, i+1)+" != Empty" );
        }

        return "lol";
    }

}

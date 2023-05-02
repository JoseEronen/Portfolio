
public class Palindrome {
    public static void main (String[] args){
        boolean result = isPalindrome("tissiit");
        System.out.println(result);
    }
    public static boolean isPalindrome(String wrd) {
        String reverseWord="";

        for (int i = 0; i <wrd.length();i++){
            reverseWord =  wrd.substring(i, i+1)+reverseWord;
        }
        System.out.println(wrd);
        System.out.println(reverseWord);
        
        int doesntMatch = 0;
        int match =0;
        
        for (int i = 0; i<wrd.length();i++){
            if (wrd.substring(i,i+1).equals(reverseWord.substring(i,i+1))){
                match = match++;
            } else if (wrd.substring(i,i+1) != reverseWord.intern()){
                doesntMatch = doesntMatch++;
                System.out.println("Letter number: "+i+" '"+reverseWord.substring(i,i+1) +"' Need to be changed to "+ wrd.substring(i, i+1) +" match.");
            }
        }
        

        if (wrd.equals(reverseWord)){
            return true;
        }else{
            return false;
        }
    }
}

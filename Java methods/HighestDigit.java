import java.util.Arrays;

public class HighestDigit {
    public static void main (String[] args){
        int result = highestDigit(1259564223);
        System.out.println("Highest digit is: " +result);
    }
    public static int highestDigit (int num){
        
        String lengthString = String.valueOf(num);
        String[] numList = new String[String.valueOf(lengthString).length()]; 
        for (int i = 0;i<lengthString.length();i++){
            numList[i] = lengthString.substring(i, i+1);
        }
        int[] numbers = new int[numList.length];
        for(int i = 0;i < numList.length;i++){
            numbers[i] = Integer.parseInt(numList[i]);
        }
        System.out.println("Array before sorting: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("Array after sorting: "+ Arrays.toString(numbers));
        return numbers[numbers.length -1];
    }
}

import java.util.Arrays;

public class ArrayOfMultiples {
    
    public static void main(String[] args) {
      
        int[] testi = arrayOfMultiples(7,5);
        System.out.println(Arrays.toString(testi));
    }
    public static int[] arrayOfMultiples(int num, int length) {
      int arr[] = new int[length];
        
        for (int i = 1;i<length+1;i++){
            int multiple = num*i;
            arr[i-1] = multiple;
       
        }
        
        
    return arr;
       

    }

}

//arrayOfMultiples(7, 5) ➞ [7, 14, 21, 28, 35]
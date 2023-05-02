
import java.util.*;

public class Mean {
    public static void main (String[] args){
        double result = mean(new int[]{1,3,3,});
        System.out.println("Mean: " + result);
    }
    public static double mean (int[] num){
        int sum = 0;
        double indexCount = 0.00;

        for (int i = 0; i< num.length;i++){
            sum+= num[i];
            ++indexCount;
        }
        double mean = sum / indexCount;
     
        Formatter formatter = new Formatter();
        formatter.format("%.2f", mean);
        formatter.close();
        return mean;
    }
  
}

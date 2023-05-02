

public class ArraySum {
    public static void main (String[] args){
        
        int summa = arraySum(new int[]{1,2,3,4}); // = 10
        System.out.println(summa);
    }

    public static int arraySum (int[] sum){
        int sumReturn = 0;
        for (int i = 0; i<sum.length;i++){
            sumReturn+= sum[i];
        }
        return sumReturn;
    }
}

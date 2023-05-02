import java.util.*;

public class Fibbonacci{
    private static Scanner input = new Scanner(System.in);
    public static void main (String[] args){
  


        System.out.println("Haettava Fibbionaccin luku?");
        int luku = input.nextInt();
        int _int[] = fibbonacci(luku);
        System.out.println(Arrays.toString(_int));
        int haettava = search(luku);
        System.out.println("Fibbionaccin "+luku + " luku on: " +(haettava));
    }
    public static int[] fibbonacci (int n){
        int[] arr = new int[n];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i<n; i++){
            arr[i] = arr[i-2] +arr[i-1];

        }
        return arr;
    }
    public static int search(int n){
        int _int[] = fibbonacci(n);
        int haettavaLuku = _int[n-1];
        return haettavaLuku;
    }

}
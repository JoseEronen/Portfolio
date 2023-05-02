

public class ExistHigher {
public static void main(String[] args){
    /* 
    int[] testi;

    for (int i = 0;i<10;i++){
        Random rnd = new Random();
        int rndInteger = rnd.nextInt(100);
        testi.add(rndInteger);
    }
    for (int showList : testi) {
        System.out.println(showList);
    }
*/
    int[] arr = { 13, 7, 6, 45, 21, 9, 101, 102 };
    boolean isTrue = existsHigher(arr, 10);
    System.out.println(isTrue);
 
    
    }

public static boolean existsHigher(int[] arr, int n) {
  
 
    for (int i = 0; i<arr.length; i++){
        if (arr[i] > n ){
        return true;
    }
    
    }
    return false;
}

}
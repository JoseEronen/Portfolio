import java.util.*;


public class WhoGoesFree {
    public static void main (String[]args){
        whoGoesFree(9, 3);
    }
    public static void whoGoesFree(int n, int k){
        List<Integer> prisoners = new ArrayList<Integer>(n);

        for (int i = 0;i<n;i++){
            prisoners.add(i); 
            
        }

        for (int prisoner : prisoners){
            System.out.print(prisoner);
        }

        while (prisoners.size() > 1){
            int start = k-1;
        /*
            for (int i = 0;i<prisoners.size();i++){
                
           }



        */

            for (int i = start;i<prisoners.size();i+=2)
            {
                System.out.print("-----------");
                prisoners.remove(i);
                for (int prisoner : prisoners){
                    System.out.print(prisoner);
                }
            }

        }

    }


}

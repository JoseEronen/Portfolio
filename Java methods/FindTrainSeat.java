//import java.util.ArrayList;
import java.util.*;

public class FindTrainSeat {
    
    public static void main (String[] args) {
        
        double rofl = findSeat(200, new double[] {70,20,5,20,5.1,7,9});
        System.out.println(rofl);
    }
	public static double findSeat(int n, double[] arr) {
		double carriageCapasity = n/arr.length;
        double[] carriages = new double[arr.length]; 
        
       
        for (int i = 0; i<arr.length;i++){
            carriages[i] = arr[i]/carriageCapasity*100*100.00/((double)100);; 
            }

            

                   
   //     Arrays.sort(carriages);

 //Muutetaan double[] lista ArrayListiksi..

      ArrayList<Double> list = new ArrayList<Double>();
    //    int list -> arraylist
        for (int i =0;i<carriages.length;i++){
            list.add(carriages[i]);
        }
    //Etsitään pienin arvo ja palautetaan arvo ja junan vaunu. 
        double mini = Collections.min(list);
        int index = -1;
        for ( int i = 0;i<list.size();i++){
            if (list.get(i)==mini){
                index = i;
            }
        }
        System.out.println("Junan vaunussa: " +index+ " on eniten tilaa. Täyttöaste: "+mini+ " %.");
        return mini;
	}
    
}

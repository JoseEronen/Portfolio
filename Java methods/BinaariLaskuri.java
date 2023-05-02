import java.util.*;

public class BinaariLaskuri {
    public static void main(String[] args) {
    ArrayList <Boolean> _list = new ArrayList<Boolean>();
    
    for (int i = 0;i<10;i++){
        Random rnd = new Random();
        int rndInteger = rnd.nextInt(10) + 1;
        
        if(rndInteger <= 5) {
            _list.add(true);
        } else {
            _list.add(false);
        }
    }
    for (boolean listShow : _list) {
        System.out.println(listShow);
    }
    int trueCount = countTrue(_list);
    System.out.println("Totuuksia: "+trueCount);

}
public static int countTrue(ArrayList<Boolean> _list){
    int trueCount = 0;
    for (boolean isTrue : _list) {
        if (isTrue == true){
            trueCount = trueCount +1;
        }

    }
    return trueCount;
}
}
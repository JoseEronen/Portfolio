import java.util.*;


public class WarOfIntegers {

    public static void main(String[] args) {
       // MainFrame myFrame = new MainFrame();
       // myFrame.init();

        ArrayList <Integer> _list = new ArrayList<Integer>();

        for (int i = 0; i<3;i++){

            Random rnd = new Random();
            int rndInteger = rnd.nextInt(10)+1;
            _list.add(rndInteger);
        }
        System.out.println("Random Numbers: "+_list);
        System.out.println("----------------------------");
        int[] list= warOfNumbers(_list);
        System.out.println(Arrays.toString(list));
        winnerCheck(list);
    }

    public static int[] warOfNumbers(ArrayList<Integer> _list){
        int oddNumbers =0;
        int evenNumbers=0;
     
        for (int i = 0;i< _list.size(); i++){
            if (_list.get(i)%2 == 0){
                evenNumbers = evenNumbers+1;
            } else {
                oddNumbers = oddNumbers +1;
            }
        }
        int[] result = new int[]{evenNumbers, oddNumbers};
        return result;
    }

    public static void winnerCheck(int[] list){
        System.out.println("----------WINNER CHECK----------");
        System.out.println("Odd Numbers:"+ list[1]);
        System.out.println("Even Numbers:"+ list[0]);
        if (list[0] > list[1]){
            System.out.println("EVEN WON! GG BRO!");
        } else if (list[0] < list[1]) {
            System.out.println("ODDS WON! GG BRAH!");
        } else{
            System.out.println("DRAW! EVERYBODY WINS! <3");
        }
    }
    
}
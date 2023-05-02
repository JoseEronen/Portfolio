import java.util.*;


public class Lottery {
    public static Scanner scanner = new Scanner(System.in);
    public static void main ( String[]args){
        int choose = 1;
        ArrayList<Integer> playerNumberList = new ArrayList<Integer>();
        Collections.addAll(playerNumberList,1,2,3,4,5,6,7);
        ArrayList<Integer> winningNumbers = lotteryNumbers(7);
        while (choose != 0){
            System.out.println("1) Play lottery");
            System.out.println("2) Change your coupon");
            System.out.println("3) Check how many times you need to play to win 7/7.");
            System.out.println("0) Quit the program.");
            choose = scanner.nextInt();
            switch(choose){
                case 1:
                winningNumbers = lotteryNumbers(7);
                checkNumbers(playerNumberList, winningNumbers);
                break;
                case 2:
                System.out.println("Your old coupon: "+ playerNumberList);
                playerNumberList = pickNumbers();
                break;
                case 3:
                howLong(playerNumberList,winningNumbers);
                case 0:
                break;
            }
        }

    /*  ArrayList<Integer> playerNumberList = pickNumbers();
        ArrayList<Integer> winningNumbers = lotteryNumbers(7);
        int sum = checkNumbers(playerNumberList, winningNumbers);
        System.out.println("Your ticket: "+playerNumberList);
        System.out.println("Winning numbers: "+winningNumbers);
        System.out.println("You have " + sum+ "/7 numbers right.");
        ArrayList<Integer> playerNumberList = pickNumbers();
        ArrayList<Integer> winningNumbers = lotteryNumbers(7);

        howLong(playerNumberList,winningNumbers);
  */
    }
//DRAW RANDOM NUMBERS
    public static ArrayList<Integer> lotteryNumbers (int num){
        ArrayList<Integer> allNumbers = new ArrayList<Integer>();

        for (int i = 1; i <= 40; i++) {
            allNumbers.add(i);
        }
        
        Collections.shuffle(allNumbers);
        
        ArrayList<Integer> winningNumbers = new ArrayList<Integer>();
        
        for (int i = 0; i < num; i++) {
            winningNumbers.add(allNumbers.get(i));
        }
        Collections.sort(winningNumbers);
        return winningNumbers;
    }
    public static ArrayList<Integer> pickNumbers (){
        ArrayList<Integer> playerNumberList = new ArrayList<Integer>();
        int num =0;

        while(playerNumberList.size()<=6){
            int showNumberIndex = playerNumberList.size()+1;
            try{
                System.out.println("Type "+showNumberIndex +" number:");
                num = scanner.nextInt();

                if (playerNumberList.contains(num)){
                    System.out.println("Can't add same number twice.");
                    continue;
                }else if (num >40||num<1){
                    System.out.println("Numbers 1-40 only.");
                    continue;
                }else{
                    playerNumberList.add(num);
                } 
            }
            catch(Exception e){
                System.out.println("Check your input."); 
                scanner.next();
            }
        } 
        return playerNumberList;
        }
        
    public static int checkNumbers (ArrayList<Integer> playerNumbers,ArrayList<Integer> winningNumbers){
    int sum = 0;    
    for (int i =0;i<=6;i++){
            if (playerNumbers.contains(winningNumbers.get(i))){
            sum++;
            }
        }
        System.out.println("Your ticket: "+playerNumbers);
        System.out.println("Winning numbers: "+winningNumbers);
        System.out.println("You have " + sum+ "/7 numbers right.");
        return sum;
    }
    public static int howLong (ArrayList<Integer> playerNumbers,ArrayList<Integer> winningNumbers){
        long startTime = System.nanoTime();
        int sum= 0;
        int howManyTickets = 0;
        boolean allTheNumbersAreSame = false;
            while (allTheNumbersAreSame == false){
                for (int i =0;i<=6;i++){
                    if (playerNumbers.contains(winningNumbers.get(i))){
                    sum++;
                    }  
                }
                howManyTickets++;
                if (sum == 7){
                    allTheNumbersAreSame =true;
                } else {
                    sum = 0;
                    winningNumbers = lotteryNumbers(7);
                }
            }
            System.out.println("your ticket: "+playerNumbers);
            System.out.println("winning ticket: "+winningNumbers);
            long stopTime = System.nanoTime();
            long timeSeconds = (stopTime - startTime)/1000 /1000 /1000;
            long timeMilSeconds = (stopTime - startTime)/1000 /1000;
            timeMilSeconds = timeMilSeconds % (int) Math.pow(10, (int) Math.log10(timeMilSeconds));
            int years = howManyTickets/52; 
            System.out.println("It took "+ howManyTickets + " shuffles to get winning ticket. Which means you should play " +years+ " years once a week to get the big prize.");
            
            System.out.println("It took "+ timeSeconds+"."+timeMilSeconds + " seconds to calculate this.");
        return howManyTickets;
    }
    
    
}


import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

  


        System.out.println("Eka luku");
        int first = input.nextInt();

        System.out.println("Operaattori");
        char operator = input.next().charAt(0);
      
        System.out.println("Toka luku");
        int second = input.nextInt();
                
        int result = calculator(first, second, operator);
        System.out.println("Calculated result: " +result);
        input.close();
    }
   

        public static int calculator(int num1, int num2, char operator) {
            if (operator == '+')
                    return num1+num2;
            if (operator == '*')
                    return num1*num2;
            if (operator == '-')
            return num1-num2;
            try {
                    return num1/num2;
            } catch (Exception et) {
                    return 0;
            }		
      }
    }

      //  MainFrame myFrame = new MainFrame();
       // myFrame.init();
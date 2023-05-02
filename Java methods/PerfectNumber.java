import java.util.ArrayList;

/*Function that tests whether or not an integer is a perfect number. A perfect number is a number that can be written as the sum of its factors, (equal to sum of its proper divisors) excluding the number itself.

For example, 6 is a perfect number, since 1 + 2 + 3 = 6, where 1, 2, and 3 are all factors of 6. Similarly, 28 is a perfect number, since 1 + 2 + 4 + 7 + 14 = 28.*/

class PerfectNumber{
    public static void main(String[]args){
        ArrayList<Integer> perfectNumList = new ArrayList<Integer>();

//Checking all the perfect numbers 0-10000 
        for (int i = 1;i<=10000;i++){
            boolean result = checkPerfect(i);
            if (result == true){
                perfectNumList.add(i);
            }
        }
//Printing the perfect numbers. Just to make sure it works :)
        for (int number : perfectNumList){
            System.out.println(number);
        }
               
    }

	public static boolean checkPerfect(int num) {

        ArrayList<Integer> factors = new ArrayList<Integer>();
//Checking if divine is 0  
		for (int i = 1;i<num;i++){
            if (num % i==0){
                factors.add(i);
            }
        }
        int sum = 0;
        for (int i=0;i<factors.size();i++){
            sum = sum+factors.get(i);
        }
        if (num == sum){
            return true;
        } else {
            return false;}
    
	}

}

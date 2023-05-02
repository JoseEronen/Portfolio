
import java.util.Arrays;

//Given the shortest side of a 30° by 60° by 90° triangle, find out the other two sides. Return the longest side and medium-length side in that order.
public class OtherSides {
    
    public static void main(String[] args) {
        double result[] = otherSides(1);
        System.out.println(Arrays.toString(result));
    }


    public static double[] otherSides(int n) {
        double hypotenuse = Math.round((n*2.00*100.0)/100.0);
        
        double squareRootOfThree = Math.sqrt(3.00); 
        
        double otherSide = n*squareRootOfThree*100.00/100.00;
        
        double triangleSides[] = new double [2];
        triangleSides[0] = Math.round(hypotenuse*100.0/100.0);
        triangleSides[1] = Math.round(otherSide*100.00)/((double)100);
        return triangleSides;
    }
    



}
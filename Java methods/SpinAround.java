
public class SpinAround {
    public static void main (String[] args){
        int testi = spinAround(new String[]{"left", "left", "left", "left","right"});
        System.out.println(testi);
    }

    public static int spinAround (String[] dir){
        int right = 0;
        int left = 0;
        int totalDeg = 0;
        
        for ( int i = 0; i<dir.length;i++){
            if (dir[i] == "right"){
                right +=90;
            } else {
                left+=90;
            }
        }
        if ( right > left){
            totalDeg = right - left;
        } else {
            totalDeg = left-right;  
        }
        int full360 = (int) Math.floor( totalDeg/360);

        System.out.println("l: " + left);
        System.out.println("r: " + right);
        System.out.println("total: "+ totalDeg);
        System.out.println("Full spins: "+ full360);

        return 0;
    }
}

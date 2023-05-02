/*fuel is the number of liters of fuel in the fuel tank.
fuelUsage is basic fuel consumption per 100 km (with the driver inside only).
Every additional passenger is increasing basic fuel consumption by 5%.
If the air conditioner is ON true, its increasing total (not basic) fuel consumption by 10%.
*/

public class MaximumDistance {
    public static void main(String[] args) {
        double result = totalDistance(10, 3.7, 2, false);
        System.out.println("Total distance: "+ String.format("%.2f",result) +" km.");
    }

    public static double totalDistance(double fuel, double fuelUsage, int passengers, boolean airConditioner) {
        
		for (int i = 0; i<passengers;i++){
            fuelUsage = fuelUsage*1.05;
        } 
        if (airConditioner = true){
            fuelUsage=fuelUsage*1.1;
        }
        double distance =fuel/fuelUsage*100;

        return distance;
    }
}

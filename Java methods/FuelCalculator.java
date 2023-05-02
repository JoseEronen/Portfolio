public class FuelCalculator {
    public static void main (String[]args){
        fuelCalculator(20,1.50,3.6);

    }
    public static void fuelCalculator(double raceLength, double laptime, double fuelConsumption){
    
    double lapsCounter = raceLength/laptime;
    int laps =(int) lapsCounter;
    System.out.println("Laps: " +laps);
    double lapsDouble = laps;
    double fuelTotal = lapsDouble*fuelConsumption;
    System.out.println("Fuel needed: "+fuelTotal+" liters.");
      
    }
}

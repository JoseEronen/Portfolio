/*The input list hours is listed sequentially, ordered from Monday to Sunday.
A worker earns $10 an hour for the first 8 hours.
For every overtime hour, he earns $15.
On weekends, the employer pays double the usual rate, regardless how many hours were worked previously that week. 
For instance, 10 hours worked on a weekday would pay 80+30 = $110, but on a weekend it would pay 160+60 = $220. */



public class WeeklySalary {
    public static void main (String[] args){
        int result = weeklySalary(new int[]{10,10,10,10,5,10,10}); 
        System.out.println(result);
    }
    public static int weeklySalary(int[] hours) {
        int[] salaryPerDay = new int[hours.length];
        int overtime = 0;
//CALCULATING FOR WEEKDAYS        
        for (int i = 0; i<=4;i++){
            if (hours[i]>8){
                overtime = (hours[i]-8)*15;
                
                salaryPerDay[i] = overtime+80;
            } else{
            salaryPerDay[i]= hours[i] *10;
            }
        }
//CALCULATING FOR WEEKEND      
        for (int i = 5; i<=6;i++){
            if (hours[i]>8){
                overtime = (hours[i]-8)*30;
                salaryPerDay[i] = overtime+160;
            } else{
            salaryPerDay[i]= hours[i] *20;
            }
        }
//CALCULATING WEEKSALARY
int sum = 0;
        for (int i =0;i<salaryPerDay.length;i++){
            System.out.println(salaryPerDay[i]);
            sum = salaryPerDay[i]+sum;
        }

        return sum;
    }
}

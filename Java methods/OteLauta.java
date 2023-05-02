import java.util.Arrays;

public class OteLauta {
    public static void main (String[] args){
        oteLauta(new String[]{"E","A","D","G","H","E"});
    }
    public static void oteLauta (String[] tune ){
        String[] notes = new String[]{"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
        String[] firstString = new String[13];
        String[] secondString = new String[13];
        String[] thirdString = new String[13];
        String[] fourthString = new String[13];
        String[] fifthString = new String[13];
        String[] sixthString = new String[13];
//taulukon indexit kuvaavat kieliä 1-6. 
        
        int firstStringIndex = -1;
        int secondStringIndex = -1;
        int thirdStringIndex = -1;
        int fourthStringIndex = -1;
        int fifthStringIndex = -1;
        int sixthStringIndex = -1;
//Etsitään oikea indexi notes listasta
//EI voi olla sama ääni kahdesti. Pitäisi purkaa tarkastamalla viritys ensin ja jos kaksi samaa niin tee jotain muuta..
for(int i =0; i<tune.length;i++){
    for(int j = i+1;j<tune.length;j++){
        if (tune[i].equals(tune[j])){

        }
    }
}

for (int i =0;i<notes.length;i++){
            if (notes[i].equals(tune[0])){
                firstStringIndex = i;
                
            }else if(notes[i].equals(tune[1])){
                secondStringIndex = i;
            }else if(notes[i].equals(tune[2])){
                thirdStringIndex = i;
            }else if(notes[i].equals(tune[3])){
                fourthStringIndex = i;
            }else if(notes[i].equals(tune[4])){
                fifthStringIndex = i;
            }else if(notes[i].equals(tune[5])){
                sixthStringIndex = i;
            }
        }

 
//1 STRING NOTES
        for (int i = 0;i<13;i++){
           
            firstString[i] = notes[firstStringIndex];
            firstStringIndex++;
            if (firstStringIndex >=notes.length){
                firstStringIndex = 0;
            }
        } 
//2 STRING NOTES
        for (int i = 0;i<13;i++){
            
            secondString[i] = notes[secondStringIndex];
            secondStringIndex++;
            if (secondStringIndex >=notes.length){
                secondStringIndex = 0;
            }
        }
//3 STRING NOTES
        for (int i = 0;i<13;i++){
            
            thirdString[i] = notes[thirdStringIndex];
            thirdStringIndex++;
            if (thirdStringIndex >=notes.length){
                thirdStringIndex = 0;
            }
        }
//4 STRING NOTES
        for (int i = 0;i<13;i++){
            
            fourthString[i] = notes[fourthStringIndex];
            fourthStringIndex++;
            if (fourthStringIndex >=notes.length){
                fourthStringIndex = 0;
            }
        }
        //5 STRING NOTES
        for (int i = 0;i<13;i++){
            
            fifthString[i] = notes[fifthStringIndex];
            fifthStringIndex++;
            if (fifthStringIndex >=notes.length){
                fifthStringIndex = 0;
            }
        }
        //6 STRING NOTES        
        for (int i = 0;i<13;i++){
            System.out.println(i);
            sixthString[i] = notes[sixthStringIndex];
            sixthStringIndex++;
            if (sixthStringIndex >=notes.length){
                sixthStringIndex = 0;
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println(Arrays.toString(firstString));
        System.out.println(Arrays.toString(secondString));
        System.out.println(Arrays.toString(thirdString));
        System.out.println(Arrays.toString(fourthString));
        System.out.println(Arrays.toString(fifthString));
        System.out.println(Arrays.toString(sixthString));
        System.out.println("----------------------------------------------");
        
    }
}   

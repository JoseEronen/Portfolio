import java.util.*;

public class jazzify {
    private static Scanner input = new Scanner(System.in);
    public static void main ( String[] args){
        System.out.println("Welcome!");
        System.out.println("1. Circle of fifths");
        System.out.println("0. Quit!");
        int choise = input.nextInt();

while (choise == 1){
        
        System.out.println("Select basechord!");
        String baseChord = input.next();
        if(baseChord=="0"){
            System.exit(0);
        }
        fifths(baseChord);
        }
    }

    public static void fifths(String baseChord){
      //  String[] majorFifth = {"C","G","D","A","E","B","F#","Db","Ab","Eb","Bb","F"};
        List<String> majorFifth = Arrays.asList("C","G","D","A","E","B","F#","Db","Ab","Eb","Bb","F");
        List<String> minorFifth = Arrays.asList("Am","Em","Bm","F#m","C#m","G#m","Ebm","Bbm","Fm","Cm","Gm","Dm");
        int majorIndex =-1;
        int minorIndex = -1;

//FIND MAJOR RELATION INDEX
        for (int i = 0; i<majorFifth.size();i++){
            if (majorFifth.get(i).equals(baseChord)){
                majorIndex = i;
            } 
        }

//FIND MINOR RELATION INDEX
        for (int i = 0; i<minorFifth.size();i++){
            if (minorFifth.get(i).equals(baseChord)){
                minorIndex = i;
            } 
        }
    
        ArrayList <String> majorRelation = new ArrayList<String>();
        ArrayList <String> minorRelation = new ArrayList<String>();
/*
        ADD INDEX EXCEPTIONS HERE
            ADD INDEX EXCEPTIONS HERE
                ADD INDEX EXCEPTIONS HERE
*/


//IF INPUT IS MAJOR CHORD
        if(minorIndex == -1) {
            if (majorIndex == 0){
                majorRelation.add(majorFifth.get(majorFifth.size() - 1)); 
                majorRelation.add(majorFifth.get(majorIndex+1));
                minorRelation.add(minorFifth.get(majorFifth.size() - 1)); 
                minorRelation.add(minorFifth.get(majorIndex+1));
                minorRelation.add(minorFifth.get(majorIndex));
            }
            else if (majorIndex == 11){
                majorRelation.add(majorFifth.get(majorIndex-1));  
                majorRelation.add(majorFifth.get(0));
                minorRelation.add(minorFifth.get(majorIndex-1));  
                minorRelation.add(minorFifth.get(0));
                minorRelation.add(minorFifth.get(majorIndex));
            } else {
                majorRelation.add(majorFifth.get(majorIndex-1)); 
                majorRelation.add(majorFifth.get(majorIndex+1)); 
                minorRelation.add(minorFifth.get(majorIndex-1)); 
                minorRelation.add(minorFifth.get(majorIndex+1));
                minorRelation.add(minorFifth.get(majorIndex)); 
            }
//IF INPUT IS MINOR CHORD
        } else if (majorIndex == -1){
            if (minorIndex == 0){
                minorRelation.add(minorFifth.get(minorFifth.size() - 1)); 
                minorRelation.add(minorFifth.get(minorIndex+1));
                majorRelation.add(majorFifth.get(minorFifth.size() - 1)); 
                majorRelation.add(majorFifth.get(minorIndex+1));
                majorRelation.add(majorFifth.get(minorIndex));
            }
            else if (majorIndex == 11){
                minorRelation.add(minorFifth.get(minorIndex-1));  
                minorRelation.add(minorFifth.get(0));
                majorRelation.add(majorFifth.get(minorIndex-1));  
                majorRelation.add(majorFifth.get(0));
                majorRelation.add(majorFifth.get(minorIndex));
            } else {
                minorRelation.add(minorFifth.get(minorIndex-1)); 
                minorRelation.add(minorFifth.get(minorIndex+1)); 
                majorRelation.add(majorFifth.get(minorIndex-1)); 
                majorRelation.add(majorFifth.get(minorIndex+1));
                majorRelation.add(majorFifth.get(minorIndex)); 
            }
        }



    System.out.println("Major Chords: "+majorRelation);
    System.out.println("Minor Chords: "+minorRelation);
    }
}

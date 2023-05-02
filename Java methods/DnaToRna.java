/*Transcribe the given DNA strand into corresponding mRNA -
a type of RNA, that will be formed from DNA after transcription.
DNA has the bases A, T, G and C, while RNA converts to U, A, C and G 
respectively. */

public class DnaToRna {
    public static void main (String[] args){dnaToRna("ATTAGCGCGATATACGCGTAC");}

	public static String dnaToRna(String strand) {
        char [] listChar = strand.toCharArray();
        System.out.println("input: "+ new String(listChar));
		    for(int i = 0; i <listChar.length; i++) {
                switch(listChar[i]) {
                    //A = U 
                    //T = A  
                    //G = C  
                    //C = G
                    case 'A' : listChar[i] = 'U';
                        break;
                    case 'T' : listChar[i] = 'A';
                        break;
                    case 'G' : listChar[i] = 'C';
                        break;
                    case 'C' : listChar[i] = 'G';
                        break;
            }
	    }
        String string = new String(listChar);
        System.out.println("output: "+string);
        return string;

}
    }

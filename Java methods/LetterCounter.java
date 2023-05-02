public class LetterCounter {
    
    public static void main (String[] args){
        int testi = letterCounter(new char[][]{
            {'D', 'E', 'Y', 'H', 'A', 'D'},
            {'C', 'B', 'Z', 'Y', 'J', 'K'},
            {'D', 'B', 'C', 'A', 'M', 'N'},
            {'F', 'G', 'G', 'R', 'S', 'R'},
            {'V', 'X', 'H', 'A', 'S', 'S'}
    }, 'D');

    System.out.println(testi);
    }
	public static int letterCounter(char[][] arr, char c) {
        int count = 0;
        for (int i = 0; i<arr.length;i++){
            
            for (int j = 0; j < arr[i].length;j++){
                if(arr[i][j]==c){
                    count++;
                }
            }
        }
        /* Vaihtoehtoinen tapa.. selkeämpi?
        for (char[] i: arr){
            for (char j : i){
                if(j==c){
                count++;
                }
            }
        }*/
		return count;
	}
}

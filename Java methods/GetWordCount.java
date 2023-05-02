
public class GetWordCount {
    public static void main(String[] args) {
       int wordCount = countWord("Just an example here move along");
        System.out.println("Words:" +wordCount);
    }

    public static int countWord(String s) {
        if(s==null || s.isEmpty()){
		return 0;
	}
    String[] words = s.split("\\s+");
    for (String nimi : words) {
        System.out.println(nimi);
    }
    return words.length;
}
}


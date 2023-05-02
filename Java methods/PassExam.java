

public class PassExam {
    public static void main (String[] args){
        String result = gradePercentage("14%", "15%");
        System.out.println(result);
    }

        public static String gradePercentage(String userScore, String passScore){
          int user = Integer.parseInt(userScore.substring(0,userScore.length()-1));
          int pass = Integer.parseInt(passScore.substring(0,passScore.length()-1));
          String s = "";
          System.out.println(user);
          System.out.println(pass);
          if (user >= pass){
            s += "PASSED";
          } else if (user <= pass){
            s += "FAILED";
          }
     
          return "You " +s+" the Exam";
          }
      }



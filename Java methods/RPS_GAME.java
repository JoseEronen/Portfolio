package RPS_GAME;

import java.util.*;
class App{
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String playerInput ="";
		Player player = new Player(0,"");
		Player opponent = new Player(0,"");

		while (!playerInput.equals("Q")){
			System.out.println("Pick R/P/S");
			playerInput = scanner.nextLine().toUpperCase();
			player.setRps(playerInput);
			

			RandomRPS randomRPS = new RandomRPS();
			String opponentInput = randomRPS.pickRandomMove();
			opponent.setRps(opponentInput);
			
			checkResults(player,opponent);
			
			System.out.println("You Pick: "+player.getRps());
			System.out.println("Opponet Pick: "+opponent.getRps());
			System.out.println("player: "+player.getWin()+" "+"opponent: "+opponent.getWin() );
		}
		System.out.println("Closing program....");
		System.exit(0);
	}
	static class RandomRPS {
	    private Random random;

	    public RandomRPS() {
	        random = new Random();
	    }
	    public String pickRandomMove() {
	    	int randomNumber = random.nextInt(3);
	    	switch (randomNumber) {
	    	case 0: 
	    		return "R";
	    	case 1:
	    		return "P";
	    	case 2:
	    		return "S";
	    	default:
	    		return "";
	    	}
			
	    }
	}
	public static class Player{
		int win;
		String rps;
		
		public Player(int win, String rps){
			this.win = win;
			this.rps = rps;
		}
		
		public int getWin() {return win;}
		public String getRps() {return rps;}
		
		public void addWin() {
		this.win = win+1;
		}
		
		public void setRps(String rps) {
			 this.rps=rps.toUpperCase();
		}
	}
	static void checkResults(Player player,Player opponent){
		String playerPick = player.getRps();
		String opponentPick = opponent.getRps();
		
        if (playerPick.equals(opponentPick)) {
          System.out.println("TIE");
        } else if ((playerPick.equals("R") && opponentPick.equals("S")) ||
                   (playerPick.equals("S") && opponentPick.equals("P")) ||
                   (playerPick.equals("P") && opponentPick.equals("R"))) {
        	player.addWin();
        } else {d
        	opponent.addWin();
        }
        if (player.getWin() == 3) {
        	System.out.println("You win! (" + player.getWin() + " - " + opponent.getWin() + ")");
        	System.out.println("Closing program....");
        	System.exit(0);
        } else if(opponent.getWin() == 3){ 
        	System.out.println("Computer wins!" +opponent.getWin() + " - " +player.getWin() );
        	System.out.println("Closing program....");
        	System.exit(0);	
        }
        
        
    }
		
	}


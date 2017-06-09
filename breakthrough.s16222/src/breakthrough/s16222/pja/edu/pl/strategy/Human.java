package breakthrough.s16222.pja.edu.pl.strategy;

import breakthrough.s16222.pja.edu.pl.Game;
import breakthrough.s16222.pja.edu.pl.UserCommunication;

public class Human implements Strategy{
	
	private UserCommunication userCommunication = new UserCommunication();

	@Override
	public String findAvaibleMove(Game game) {
		String move="";
		do{  
			move = userCommunication.getInformation();
			if(move.length() < 4){
				System.out.println("You should enter 4 characters. Try again");
			}
			
		} while (move.length() < 4);
		return move;
	}

}

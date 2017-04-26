package breakthrough.s16222.pja.edu.pl.strategy;

import breakthrough.s16222.pja.edu.pl.Game;

public class Human implements Strategy{

	@Override
	public String findAvaibleMove(Game game) {
		String move="";
		do{  
			move = sc.nextLine();
			
		} while (move.length() < 4);
		return move;
	}

}

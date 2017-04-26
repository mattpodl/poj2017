package breakthrough.s16222.pja.edu.pl.strategy;

import breakthrough.s16222.pja.edu.pl.*;

public class Random implements Strategy{
	
	@Override
	public String findAvaibleMove(Game game){
		String move ="";
		Board temp;// = new Board();
		char which_column;
		int which_row;
		temp = game.findBoard();
		which_column = temp.getLocationVertical();
		which_row = temp.getLocationHorizontal();
		move+=which_column+""+which_row;
		if(game.isMoveAvaible(which_column,which_row,which_column,which_row-1)){
			move+=which_column+""+(which_row-1);
		} else if(game.isMoveAvaible(which_column,which_row,(char)(which_column-1),which_row-1)){
			move+=(char)(which_column-1)+""+(which_row-1);
		} else if (game.isMoveAvaible(which_column,which_row,(char)(which_column+1),which_row-1)){
			move+=(char)(which_column+1)+""+(which_row-1);
		} else {
			move = "didn't find any";
		}
		
		return move;
	}
}

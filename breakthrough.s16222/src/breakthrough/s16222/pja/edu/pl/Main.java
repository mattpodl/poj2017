package breakthrough.s16222.pja.edu.pl;


import breakthrough.s16222.pja.edu.pl.strategy.*;


public class Main {
	int roundCounter = 0;
	static Strategy blackPawns = null, whitePawns = new Human();
	static String temp = "";


	public static void main(String[] args) {
		Game game = new Game();
		blackPawns = game.startGame();
		while (game.endGame().equals(PawnAvaible.NONE)) {
			game.printChessboard();
			System.out.println("White player move: ");
			while (!(game.move(whitePawns, PawnAvaible.WHITE)));
			if (!(game.endGame().equals(PawnAvaible.NONE))) {
				break;
			}
			System.out.println("Black player move: ");

			
			while (!(game.move(blackPawns, PawnAvaible.BLACK)));
			
		}
		if (game.endGame().equals(PawnAvaible.WHITE)) {
			System.out.println("White color wins!");
		} else {
			System.out.println("Black color wins!");
		}

	}

}

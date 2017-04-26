package breakthrough.s16222.pja.edu.pl;

import java.util.Scanner;

import breakthrough.s16222.pja.edu.pl.strategy.*;


public class Main {
	int roundCounter = 0;
	static Scanner sc = new Scanner(System.in);
	static Strategy blackPawns = null, whitePawns = new Human();
	static String temp = "";


	public static void main(String[] args) {
		Game game = new Game();
		blackPawns = game.startGame();
		while (game.endGame().equals(PawnAvaible.NONE)) {
			game.printChessboard();
			// ruch bialego
			System.out.println("Ruch bialego: ");
			while (!(game.move(whitePawns, PawnAvaible.WHITE)));
			if (!(game.endGame().equals(PawnAvaible.NONE))) {
				break;
			}
			System.out.println("Ruch czarnego: ");

			// ruch czarnego
			while (!(game.move(blackPawns, PawnAvaible.BLACK)));
			// logika gry
		}
		if (game.endGame().equals(PawnAvaible.WHITE)) {
			System.out.println("White color wins!");
		} else {
			System.out.println("Black color wins!");
		}

	}

}

package breakthrough.s16222.pja.edu.pl.strategy;

import java.util.Scanner;

import breakthrough.s16222.pja.edu.pl.Game;

public interface Strategy {
	Scanner sc = new Scanner(System.in);

	public String findAvaibleMove(Game game);
}

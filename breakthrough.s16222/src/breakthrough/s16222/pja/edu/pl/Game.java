package breakthrough.s16222.pja.edu.pl;

import breakthrough.s16222.pja.edu.pl.strategy.*;

public class Game {
	private static final int SIZE_OF_BOARD = 8;
	
	private UserCommunication userCommunication;
	private Board[][] board = new Board[SIZE_OF_BOARD][SIZE_OF_BOARD];

	public void printChessboard() {
		System.out.println("  A    B    C    D    E    F    G    H ");
		for (int i = 0, row = 8; i < 8; i++, row--) {
			System.out.print(row + " ");
			for (int j = 0; j < 8; j++) {

				System.out.print(board[i][j].returnLocation() + " | ");
			}
			System.out.println("");
			System.out.println("  ---------------------------------------");
		}
	}

	public Game() {
		userCommunication = new UserCommunication();
		for (int i = 0, row = 8; i < 8; i++, row--) {
			char column = 0;
			for (int j = 0; j < 8; j++) {
				column = ((char) (j + 97));
				if (i <= 1) {
					board[i][j] = new Board(PawnAvaible.BLACK, row, column);
				} else if (i > 1 && i < 6) {
					board[i][j] = new Board(PawnAvaible.NONE, row, column);
				} else {
					board[i][j] = new Board(PawnAvaible.WHITE, row, column);
				}
			}

		}

	}

	public boolean isMoveAvaible(char whichColumn, int whichRow, char whereColumn, int whereRow) {

		PawnAvaible which, where;
		if (findBoard(whichColumn, whichRow) == null || findBoard(whereColumn, whereRow) == (null)) {
			return false; // move is outside of chessboard
		}
		
		which = this.findBoard(whichColumn, whichRow).getLocalPawn();
		where = this.findBoard(whereColumn, whereRow).getLocalPawn();

		if (which == PawnAvaible.WHITE) {
			if (!(whichRow - whereRow == -1)) {
				return false;
			}
			if (whereColumn - whichColumn == 1 || whereColumn - whichColumn == -1) {
				return !(where == PawnAvaible.WHITE);
			}
			if (whereColumn == whichColumn) {
				return (where == PawnAvaible.NONE);
			}
			return false;

		}
		if (which.equals(PawnAvaible.BLACK)) {
			if (!(whichRow - whereRow == 1)) {
				return false;
			}
			if (whereColumn - whichColumn == 1 || whereColumn - whichColumn == -1) {
				return !(where == PawnAvaible.BLACK);
			}
			if (whereColumn == whichColumn) {
				return (where == PawnAvaible.NONE);
			}
			return false;

		}
		return false;

	}

	public Board findBoard(char locationVertical, int locationHorizontal) {
		for (int i = 0; i < 8; i++) {
			if (board[i][0].getLocationHorizontal() == locationHorizontal) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j].getLocationVertical() == locationVertical) {
						return board[i][j];
					}
				}

			}
		}
		return null;
	}

	public Board findBoard() {
		for (int i = 7; i >= 0; i--) {

			for (int j = 0; j < 8; j++) {
				if (board[i][j].getLocalPawn().equals(PawnAvaible.BLACK))
					return board[i][j];
			}
		}
		return null;
	}

	private boolean move(char whichColumn, int whichRow, char whereColumn, int whereRow) {
		PawnAvaible temp;
		temp = findBoard(whichColumn, whichRow).getLocalPawn();
		findBoard(whichColumn, whichRow).setLocalPawn(PawnAvaible.NONE);
		findBoard(whereColumn, whereRow).setLocalPawn(temp);
		this.printChessboard();
		return true;
	}

	public PawnAvaible endGame() {
		int black = 0, white = 0;
		for (int j = 0; j < 8; j++) {
			if (board[0][j].getLocalPawn().equals(PawnAvaible.WHITE))
				return PawnAvaible.WHITE;
		}
		for (int j = 0; j < 8; j++) {
			if (board[7][j].getLocalPawn().equals(PawnAvaible.BLACK))
				return PawnAvaible.BLACK;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getLocalPawn().equals(PawnAvaible.BLACK))
					white++;
				if (board[i][j].getLocalPawn().equals(PawnAvaible.WHITE))
					black++;
			}
		}
		if (black == 0)
			return PawnAvaible.WHITE;
		if (white == 0)
			return PawnAvaible.BLACK;

		return PawnAvaible.NONE;
	}

	public boolean move(Strategy strategy, PawnAvaible pawn) {
		String move = strategy.findAvaibleMove(this);
		char whichColumn, whereColumn;
		int whichRow, whereRow;
		whichColumn = move.charAt(0);
		whichRow = move.charAt(1) - 48;
		whereColumn = move.charAt(2);
		whereRow = move.charAt(3) - 48;
		if (!(isMoveAvaible(whichColumn, whichRow, whereColumn, whereRow))) {
			System.out.println("This move isn't allowed. Try again.");
			return false;
		}
		if (this.findBoard(whichColumn, whichRow).getLocalPawn().equals(pawn)) {
			return this.move(whichColumn, whichRow, whereColumn, whereRow);

		} else {
			System.out.println("You can move only your pawns. Try again.");
			return false;
		}
	}

	public Strategy startGame() {
		Strategy blackPawns = null;
		String temp;
		System.out.println("\"BREAKTHROUGHT\"");
		System.out.println("You will start game with white pawn(BB), choose your opponent(CZ)");
		do {
			System.out.println("enter (h) for human player\n(c) for computer player\n (ai) for AI ;)");
			temp = userCommunication.getInformation();
			if (temp.charAt(0) == 'h') {
				blackPawns = new Human();
			} else if (temp.charAt(0) == 'c') {
				blackPawns = new Random();
			} else if (temp.charAt(0) == 'a') {
				blackPawns = new Inteligent();
			} else {

				System.out.println("This move isn't allowed. Choose field with your pawn.");
			}
		} while (blackPawns == null);

		return blackPawns;
	}
}

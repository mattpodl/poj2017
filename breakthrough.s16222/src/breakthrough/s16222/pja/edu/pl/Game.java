package breakthrough.s16222.pja.edu.pl;

import java.util.Scanner;

import breakthrough.s16222.pja.edu.pl.strategy.Human;
import breakthrough.s16222.pja.edu.pl.strategy.Inteligent;
import breakthrough.s16222.pja.edu.pl.strategy.Random;
import breakthrough.s16222.pja.edu.pl.strategy.Strategy;

public class Game {
	private static final int SIZE_OF_BOARD = 8;
	
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
		//System.out.println("\n");
	}
	
	public Game() {
		for (int i = 0, row = 8; i < 8; i++, row--) {
			char column = 0;
			for (int j = 0; j < 8; j++) {
				switch (j + 1) {
				case 1:
					column = 'a';
					break;
				case 2:
					column = 'b';
					break;
				case 3:
					column = 'c';
					break;
				case 4:
					column = 'd';
					break;
				case 5:
					column = 'e';
					break;
				case 6:
					column = 'f';
					break;
				case 7:
					column = 'g';
					break;
				case 8:
					column = 'h';
					break;

				}
				if (i <= 1) {
					board[i][j] = new Board(PawnAvaible.BLACK, row, column);
				} else if (i > 1 && i < 6) {
					board[i][j] = new Board(PawnAvaible.NONE,  row, column);
				} else {
					board[i][j] = new Board(PawnAvaible.WHITE, row, column);
				}
			}

		}

	}

	public boolean isMoveAvaible(char which_column, int which_row, char where_column, int where_row) {
		
		PawnAvaible which, where;
		if (findBoard(which_column, which_row) == null || findBoard(where_column, where_row) == (null)) {
			return false; //move is outside of chessboard
		} else {
			which = this.findBoard(which_column, which_row).getLocalPawn(); 
			where = this.findBoard(where_column, where_row).getLocalPawn();
		} 
		

		if (which == PawnAvaible.WHITE){
			if (!(which_row-where_row == -1)){
				return false;
			}
			if (where_column-which_column == 1 || where_column-which_column == -1){
				return !(where == PawnAvaible.WHITE);
			} else if (where_column == which_column) {
				return (where == PawnAvaible.NONE);
			} else {
			return false;
			}
		} else if (which.equals(PawnAvaible.BLACK)){
			if (!(which_row-where_row == 1)){
				return false;
			}
			if (where_column-which_column == 1 || where_column-which_column == -1){
				return !(where == PawnAvaible.BLACK);
			} else if (where_column == which_column) {
				return (where == PawnAvaible.NONE);
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public Board findBoard(char locationVertical, int locationHorizontal){
		for (int i = 0; i<8;i++){
			if (board[i][0].getLocationHorizontal() == locationHorizontal){
				for (int j = 0; j<8;j++){
					if(board[i][j].getLocationVertical() == locationVertical){
						return  board[i][j];
					}
				}
				
			}
		}
		return null;
	}
	public Board findBoard(){
		for (int i = 7; i >= 0; i--) {
			
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getLocalPawn().equals(PawnAvaible.BLACK)) return board[i][j];
			}
		}
		return null;
	}
	
	private boolean move(char which_column, int which_row, char where_column, int where_row) {
			PawnAvaible temp ;
			temp = findBoard(which_column, which_row).getLocalPawn();
			findBoard(which_column, which_row).setLocalPawn(PawnAvaible.NONE);
			findBoard(where_column, where_row).setLocalPawn(temp);
			this.printChessboard();
			return true;
	}
	public PawnAvaible endGame(){
		int black = 0, white = 0;
		for (int j = 0; j<8; j++){
			if (board[0][j].getLocalPawn().equals(PawnAvaible.WHITE)) return PawnAvaible.WHITE;
		}
		for (int j = 0; j<8; j++){
			if (board[7][j].getLocalPawn().equals(PawnAvaible.BLACK)) return PawnAvaible.BLACK;
		}
		for (int i = 0; i<8;i++){
				for (int j = 0; j<8;j++){
					if (board[i][j].getLocalPawn().equals(PawnAvaible.BLACK)) white++;
					if (board[i][j].getLocalPawn().equals(PawnAvaible.WHITE)) black++;
				}
		}
		if (black == 0) return PawnAvaible.WHITE;
		if (white == 0) return PawnAvaible.BLACK;
		
		return PawnAvaible.NONE; 
	}

	public boolean move(Strategy strategy, PawnAvaible pawn) {
		String move = strategy.findAvaibleMove(this);
		char which_column, where_column;
		 int which_row, where_row;
		which_column = move.charAt(0);
		which_row = move.charAt(1) - 48;
		where_column = move.charAt(2);
		where_row = move.charAt(3) - 48;
		if (!(isMoveAvaible( which_column,  which_row,  where_column,  where_row))){
			System.out.println("This move isn't allowed. Try again.");
			return false;
		}
		if (this.findBoard(which_column, which_row).getLocalPawn().equals(pawn)) {
			return this.move(which_column, which_row, where_column, where_row);

		} else {
			System.out.println("You can move only your pawns. Try again.");
			return false;
		}
	}

	public Strategy startGame() {
		Scanner sc = new Scanner(System.in);
		Strategy blackPawns=null;
		String temp;
		System.out.println("\"BREAKTHROUGHT\"");
		System.out.println("You will start game with white pawn(BB), choose your opponent(CZ)");
		do {
			System.out.println("enter (h) for human player\n(c) for computer player\n (ai) for AI ;)");
			temp = sc.nextLine();
			if (temp.charAt(0)=='h'){
				blackPawns = new Human();
			} else if (temp.charAt(0)=='c'){
				blackPawns = new Random();
			} else if (temp.charAt(0)=='a'){
				blackPawns = new Inteligent();
			}	else {
			
				System.out.println("This move isn't allowed. Choose field with your pawn.");
			}
		} while (blackPawns == null);
		
		return blackPawns;
	}
}

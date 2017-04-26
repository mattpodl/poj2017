package breakthrough.s16222.pja.edu.pl;

public class Board {
	private PawnAvaible localPawn;
	private int locationHorizontal;
	private char locationVertical;
	
	
	
	
	public String returnLocation() {
		String location="";
		if (localPawn.equals(PawnAvaible.BLACK)){
			location="BL";
		}
		else if (localPawn.equals(PawnAvaible.WHITE)){
			location="WH";
		} 
		else location="  ";
		return location;
	}
	public Board(){
		localPawn = PawnAvaible.NONE;
		this.locationVertical = 1;
		this.locationHorizontal = 1;
	}
	public Board(PawnAvaible localPawn,  int locationHorizontal, char locationVertical) {
		
		this.localPawn = localPawn;
		this.locationVertical = locationVertical;
		this.locationHorizontal = locationHorizontal;
	}
	
	public int getColumn(){
		switch (locationVertical) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		default:
			return 8;
		}
	}
	
	public int getLocationHorizontal() {
		return locationHorizontal;
	}
	public char getLocationVertical() {
		return locationVertical;
	}
	public PawnAvaible getLocalPawn() {
		return localPawn;
	}
	public void setLocalPawn(PawnAvaible localPawn) {
		this.localPawn = localPawn;
	}
	public int getRow(){
		switch (locationHorizontal) {
		case 1:
			return 7;
		case 2:
			return 6;
		case 3:
			return 5;
		case 4:
			return 4;
		case 5:
			return 3;
		case 6:
			return 2;
		case 7:
			return 1;
		case 8:
			return 0;
		default:
			return 8;
		}
	}
	
}

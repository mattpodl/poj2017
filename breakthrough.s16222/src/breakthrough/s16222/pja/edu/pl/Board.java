package breakthrough.s16222.pja.edu.pl;

public class Board {
	private PawnAvaible localPawn;
	private int locationHorizontal;
	private char locationVertical;
	
	
	
	// method needed to print game board
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
		return ((int)locationVertical) - 97;
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
		return 8 - locationHorizontal; 
	}
	
}

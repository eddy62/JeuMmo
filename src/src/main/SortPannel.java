package src.main;

public enum SortPannel {
	FEU(10),
	GLACE(10),
	EAU(10),
	VENT(10);
	
	
	private int degats;
	
	private SortPannel(int degats) {
		this.degats = degats;
	}
}

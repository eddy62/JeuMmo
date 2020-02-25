package src.main;

public class Sorcier extends Personnage {

	private static int santeInitiale = 80;
	
	public Sorcier() {
		super(santeInitiale);
		ajouterSort(new Sort("FEU", 10));
		//ajouterSort(SortPannel.FEU);
	}
}

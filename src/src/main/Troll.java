package src.main;

public class Troll extends Monstre {

	private static int santeInitiale = 200;
	private static float reductionDegat = 0.9f;

	public Troll() {
		super(Troll.santeInitiale);
	}

	public void subirDegat(int degats) {
		super.subirDegat((int) (degats * reductionDegat));
	}
}

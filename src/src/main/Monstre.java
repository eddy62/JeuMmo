package src.main;

public abstract class Monstre implements Combattant {

	private int sante;
//	private String nom;

	protected Monstre(int sante) {
		this.sante = sante;
	}

	public boolean isAlive() {
		return this.sante > 0;
	}

	public void subirDegat(int degats) {
		this.sante -= degats;
		if (!this.isAlive())
			this.sante = 0;
		System.out.println(this + " a perdu " + degats + " points de vie !");
	}

	public String toString() {
		return this.getClass().getSimpleName();
	}
}

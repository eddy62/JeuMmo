package src.main;

import java.io.Serializable;
import java.util.HashSet;

public abstract class Personnage implements Combattant {

	private int niveau;
	private int sante;
	private int niveauExp;
	private int prochainNiveau;
	private int santeMax;
	private HashSet<Sort> sorts;

	private static double facteurNiveauSuivant = 1.5;

	public int getSanteMax() {
		return santeMax;
	}

	public void setSanteMax(int santeMax) {
		this.santeMax = santeMax;
	}

	private Personnage() {
		this.niveau = 1;
		this.niveauExp = 0;
		this.prochainNiveau = 1000;
		this.sorts = new HashSet<Sort>();
	}

	protected Personnage(int sante) {
		this();
		this.sante = sante;
		this.setSanteMax(sante);
	}

	public int getNiveau() {
		return niveau;
	}

	public int getSante() {
		return sante;
	}

	public void setSante(int sante) {
		this.sante = sante;
	}

	public void subirDegat(int degats) {
		this.sante -= degats;
		if (this.sante < 0)
			this.sante = 0;
		System.out.println(this.getClass().getSimpleName() + " a perdu " + degats + " points de vie !");
	}

	public boolean isAlive() {
		return this.sante > 0;
	}

	public int getNiveauExp() {
		return niveauExp;
	}

	public void mettreAJourExp(int expGagne){
		this.niveauExp += expGagne;
		System.out.println(this + " a gagner " + expGagne + " EXP");
		while (this.niveauExp >= this.prochainNiveau) {
			this.niveauExp -= this.prochainNiveau;
			this.niveau++;
			this.prochainNiveau *= Personnage.facteurNiveauSuivant;
			
			System.out.println(this.getClass().getSimpleName() + " passe du niveau " + (this.niveau - 1) + " au niveau "
					+ this.niveau);
		}
	}

	public int getProchainNiveau() {
		return prochainNiveau;
	}

	public HashSet<Sort> getSorts() {
		return sorts;
	}

	protected boolean ajouterSort(Sort s) {
		return this.sorts.add(s);
	}

	public String toString() {
		return "'" + this.getClass().getSimpleName() + "' de niveau '" + this.getNiveau() + "' et d'EXP '"
				+ this.getNiveauExp() + "/" + this.getProchainNiveau() + "'";
	}
}

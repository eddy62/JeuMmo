package src.main;

import java.io.Serializable;
import java.util.Objects;

public class Joueur implements Serializable{

	private String pseudo;
	private Personnage personnage;

	public Joueur(String pseudo, Personnage personnage) {
		this.pseudo = pseudo;
		this.personnage = personnage;
	}

	/*
	 * public void lancerCombat(Joueur adversaire) { int expEnJeu = 2000; Joueur
	 * victime = this; Joueur attaquant = adversaire; do{ Joueur temp = victime;
	 * victime = attaquant; attaquant = temp;
	 * System.out.println("#########################################");
	 * System.out.println(attaquant.pseudo+" attaque "+victime.pseudo);
	 * victime.personnage.subirDegat(10); try { Thread.sleep(1000); } catch
	 * (InterruptedException e) { e.printStackTrace(); System.exit(1); } }while(
	 * victime.personnage.getSante() > 0 );
	 * System.out.println("#########################################");
	 * System.out.println(victime.pseudo+" a perdu !");
	 * System.out.println(attaquant.pseudo+" a gagner "+expEnJeu+" EXP");
	 * attaquant.personnage.mettreAJourExp(expEnJeu); }
	 */

	public Personnage getPersonnage() {
		return this.personnage;
}

	public String toString() {
		return "'" + this.pseudo + "' utilisant un personnage " + this.personnage + "";
	}

	public boolean equals(Object j) {
		if (j != null && j instanceof Joueur)
			return this.pseudo.equals(((Joueur) j).pseudo);
		return false;
	}

	public int hashCode() {
		return Objects.hashCode(this.pseudo);
	}
}

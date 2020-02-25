package src.main;

import java.util.Objects;

public class Sort {
	
	private String nom;
	private int degats;

	public Sort(String nom, int degats) {
		this.nom = nom;
		this.degats = degats;
	}
	
	public String toString() {
		return "sort de '"+this.nom+"' qui inflige '"+this.degats+"' points de dégats.";
	}

	public boolean equals(Object s) {
		if (s != null && s instanceof Sort)
			return this.nom.equals(((Sort)s).nom);
		return false;
	}
	
	public int hashCode() {
		return Objects.hashCode(this.nom);
	}
}

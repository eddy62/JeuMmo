package src.main;

import java.io.Serializable;

public interface Combattant extends Serializable{
	public void subirDegat(int degats);

	public boolean isAlive();

	default void mettreAJourExp(int exp){
	}
}

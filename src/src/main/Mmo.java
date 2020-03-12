package src.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Mmo implements Serializable {

	private HashSet<Joueur> joueurs;
	private ArrayList<Monstre> monstres;
	private Carte carte;

	private static Scanner sc = new Scanner(System.in);

	public Mmo() {
		this.joueurs = new HashSet<Joueur>();
		this.monstres = new ArrayList<Monstre>();
		this.carte = new Carte();
	}

	private boolean ajouterJoueur(Joueur j) {
		if (this.joueurs.add(j)) {
			System.out.println("Le joueur " + j + " a été ajouté");
			return true;
		}
		System.out.println("Le joueur " + j + " n'a pas été ajouté (le pseudo existe déja).");
		return false;
	}
		private boolean load(String pseudo) throws SQLException, ClassNotFoundException {
		if (CRUD.recupIdParPseudo(pseudo) != null) {
			return true;
		}
		return false;
	}

	private void creerJoueur() throws SQLException, ClassNotFoundException {
		String choixPseudo;
		String choixPersonnage = null;
		Personnage personnage = null;
		Joueur j;

		System.out.println("Veuillez entrer votre pseudo :");
		choixPseudo = sc.next();
		if (!load(choixPseudo)) {
			choixPersonnage = null;
			while (choixPersonnage == null) {
				System.out.println("Quelle personnage voulez-vous jouer ?\n\t1 - Archer\n\t2 - Soldat\n\t3 - Sorcier");
				choixPersonnage = sc.next();
				switch (choixPersonnage) {
					case "1":
						personnage = new Archer();
						break;
					case "2":
						personnage = new Soldat();
						break;
					case "3":
						personnage = new Sorcier();
						break;
					default:
						choixPersonnage = null;
						break;
				}
			}
			/*j = new Joueur(choixPseudo, personnage);*/

			CRUD.InsertJoueur(choixPseudo);
			CRUD.InsertPersonnage(personnage, CRUD.recupIdParPseudo(choixPseudo));
		} else {
			System.out.println("personnage existe");
			ResultSet r = CRUD.getperso(CRUD.recupIdParPseudo(choixPseudo));
			ResultSetMetaData rsmd = r.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (r.next()) {

				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(",  ");
					String columnValue = r.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");

			}
		}
	}


	private void creerMonstre() {
		this.monstres.add(new Troll());
	}

	public boolean sauvegarder() {
		File fichier = new File("mmo.save");
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		Boolean result = false;
		try {
			fs = new FileOutputStream(fichier);
			os = new ObjectOutputStream(fs);
			for (Joueur j : this.joueurs) {
					os.writeObject(j);
			}
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}finally {
			try {
				fs.close();
				os.close();
			} catch (IOException e) {
				System.err.println("Erreur lors de la fermeture du flux : "+e.getMessage());
			}
		}
		return result;
	}

	public void jouer() {
		System.out.println("#########################\n");
		System.out.println("LE JEU COMMENCE !");
		Object[] mesJoueurs = this.joueurs.toArray();
		Object[] mesMonstres = this.monstres.toArray();
		Combat combat = new Combat((Monstre) mesMonstres[0], ((Joueur) mesJoueurs[0]).getPersonnage(), 1000);
		combat.lancerCombat();
		// combat = new Combat(((Joueur) mesJoueurs[1]).getPersonnage(), ((Joueur)
		// mesJoueurs[0]).getPersonnage(), 2000);
		// combat.lancerCombat();
		// ((Joueur) mesJoueurs[0]).lancerCombat((Joueur) mesJoueurs[1]);
		System.out.println("#########################\n");
		System.out.println("FIN DU JEU !");
	}

	public static Mmo restaurer() {
		File save = new File("mmo.save");
		Mmo result = null;
		FileInputStream fs;
		try {
			fs = new FileInputStream(save);
			ObjectInputStream os = new ObjectInputStream(fs);
			result = new Mmo();
			try {
				while (true) {
					result.joueurs.add((Joueur) os.readObject());
				}
			} catch (EOFException e) {
				//fin de fichier
			}
			result.monstres.add(new Troll());
		} catch (FileNotFoundException e) {
			System.out.println("Pas de joueurs créés, création nécessaire.");
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Erreur lors de la lecture du fichier de sauvegarde : "+e.getMessage());
		}
		return result;
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		CRUD con = new CRUD();
		Connection cnx = con.ConnectBdd();
		Mmo mmo = new Mmo();
		mmo.creerJoueur();
		/*Mmo mmo = Mmo.restaurer();
		if (mmo == null) { // Si il y a pas de récupération possible
			mmo = new Mmo();
			mmo.creerJoueur();
			mmo.creerMonstre();
		}

		Regeneration regen = new Regeneration((Joueur) mmo.joueurs.toArray()[0], 100);
		regen.setDaemon(true);
		regen.start();
		mmo.jouer();
		mmo.sauvegarder();*/
	}
}

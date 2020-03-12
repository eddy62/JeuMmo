package src.main;

public class Regeneration extends Thread {
    Joueur perso;
    int attente;

    public Regeneration() {}

    public Regeneration(Joueur perso, int attente) {
        this.perso = perso;
        this.attente = attente;
    }

    public void run() {
        while (true) {
            try {
                sleep(attente);
            } catch (InterruptedException e) {}
            if (perso.getPersonnage().getSante() < 150) {
                perso.getPersonnage().setSante(perso.getPersonnage().getSante() + 1);
                System.out.println("plus 1 point de vie ");
            }
        }
    }
}

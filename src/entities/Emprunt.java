package entities;

import java.time.LocalDate;

public class Emprunt {
    private Livre livre;
    private Utilisateur emprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(Livre livre, Utilisateur emprunteur, LocalDate dateEmprunt) {
        this.livre = livre;
        this.emprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
    }

    @Override
    public String toString() {
        return "Emprunt:" + livre.toString() + " - " + emprunteur.toString() + " - " + dateEmprunt.toString();
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }
}

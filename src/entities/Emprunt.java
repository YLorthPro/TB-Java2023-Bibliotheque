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

    public Livre getLivre() {
        return livre;
    }

    public Utilisateur getEmprunteur() {
        return emprunteur;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }
}

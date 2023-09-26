package entities;

import java.time.LocalDate;

public class Emprunt {
    private Long id;
    private Livre livre;
    private Utilisateur emprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(Long id, Livre livre, Utilisateur emprunteur, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = id;
        this.livre = livre;
        this.emprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Emprunt:" + livre.toString() + " - " + emprunteur.toString() + " - " + dateEmprunt.toString();
    }
}

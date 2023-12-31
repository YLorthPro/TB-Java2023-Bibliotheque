package entities;

import java.util.Objects;

public class Livre {

    private Long id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String isbn;

    public Livre(Long id, String titre, String auteur, int anneePublication, String isbn) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    @Override
    public String toString() {
        return "ISBN: "+isbn+" - Titre: " +
                titre + ", Auteur: " + auteur +
                ", Année publication: " + anneePublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livre livre = (Livre) o;

        return Objects.equals(isbn, livre.isbn);
    }
}

package entities;

public class Livre {

    private String titre;
    private String auteur;
    private int anneePublication;
    private String isbn;

    public Livre(String titre, String auteur, int anneePublication, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
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

}

package entities;

import dal.dao.*;

import java.time.LocalDate;
import java.util.*;

public class Bibliotheque {

    private final LivreDAO livreDAO = new LivreDAOImpl();
    private final EmpruntDAO empruntDAO = new EmpruntDAOImpl();
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();



    public boolean ajouterLivre(Livre livre){
        return livreDAO.create(livre);
    }

    public boolean supprimerLivre(Livre livre){
        return livreDAO.delete(livre.getId());
    }

    public boolean updateLivre(String isbnToUpdate, String titre, String auteur, int anneePublication){
        Livre toUpdate = getOne(isbnToUpdate);

        if(toUpdate == null)
            return false;

        toUpdate.setTitre(titre);
        toUpdate.setAuteur(auteur);
        toUpdate.setAnneePublication(anneePublication);
        return livreDAO.update(toUpdate.getId(), toUpdate);
    }

    public Livre getOne (String isbn){
        return livreDAO.getOne(isbn).get();
    }

    public List<Livre> getAll(){
        return livreDAO.getAll();
    }

    public void afficherInfo(String isbn){
        System.out.println(getOne(isbn));
    }

    public boolean emprunterLivre(String isbn, Long emprunteur){
        if(listeLivresDispo().contains(livreDAO.getOne(isbn).get()))
            return empruntDAO.create(new Emprunt(0L, livreDAO.getOne(isbn).get(),utilisateurDAO.getOne(emprunteur).get(),LocalDate.now(),null));


        return false;
    }

    public boolean retournerLivre(String isbn){
        return empruntDAO.update(isbn,LocalDate.now());
    }

    public List<Livre> listeLivresDispo(){
        return empruntDAO.getAllDispo();
    }
}
